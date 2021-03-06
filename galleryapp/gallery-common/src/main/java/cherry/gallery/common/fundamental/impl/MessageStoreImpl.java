/*
 * Copyright 2014,2016 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.gallery.common.fundamental.impl;

import static com.google.common.base.Preconditions.checkState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import cherry.fundamental.bizcal.BizDateTime;
import cherry.fundamental.mail.MessageStore;
import cherry.gallery.db.gen.query.QMailLog;
import cherry.gallery.db.gen.query.QMailQueue;
import cherry.gallery.db.gen.query.QMailRcpt;

import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;

public class MessageStoreImpl implements MessageStore {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QMailLog ml = new QMailLog("ml");
	private final QMailRcpt mr = new QMailRcpt("mr");
	private final QMailQueue mq = new QMailQueue("mq");

	@Override
	public long createMessage(String launcherId, String messageName, LocalDateTime scheduledAt, String from,
			List<String> to, List<String> cc, List<String> bcc, String replyTo, String subject, String body) {
		long mailId = createMailLog(launcherId, bizDateTime.now(), messageName, scheduledAt, from, replyTo, subject,
				body);
		createMailRcpt(mailId, RcptType.TO.name(), to);
		createMailRcpt(mailId, RcptType.CC.name(), cc);
		createMailRcpt(mailId, RcptType.BCC.name(), bcc);
		createMailQueue(mailId, scheduledAt);
		return mailId;
	}

	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		SQLQuery<?> query = queryFactory.from(mq);
		query.where(mq.scheduledAt.loe(dtm));
		return query.select(mq.mailId).fetch();
	}

	@Override
	public SimpleMailMessage getMessage(long messageId) {

		SQLQuery<?> querya = queryFactory.from(ml).forUpdate();
		querya.where(ml.id.eq(messageId), ml.mailStatus.eq(0));
		Tuple maillog = querya.select(ml.fromAddr, ml.replyToAddr, ml.subject, ml.body).fetchOne();
		if (maillog == null) {
			return null;
		}

		SQLQuery<?> queryb = queryFactory.from(mr);
		queryb.where(mr.mailId.eq(messageId));
		queryb.orderBy(mr.id.asc());
		List<Tuple> mailrcpt = queryb.select(mr.rcptType, mr.rcptAddr).fetch();
		if (mailrcpt.isEmpty()) {
			return null;
		}

		List<String> to = new ArrayList<>();
		List<String> cc = new ArrayList<>();
		List<String> bcc = new ArrayList<>();
		for (Tuple rcpt : mailrcpt) {
			RcptType type = RcptType.valueOf(rcpt.get(mr.rcptType));
			if (type == RcptType.TO) {
				to.add(rcpt.get(mr.rcptAddr));
			}
			if (type == RcptType.CC) {
				cc.add(rcpt.get(mr.rcptAddr));
			}
			if (type == RcptType.BCC) {
				bcc.add(rcpt.get(mr.rcptAddr));
			}
		}

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to.toArray(new String[to.size()]));
		msg.setCc(cc.toArray(new String[cc.size()]));
		msg.setBcc(bcc.toArray(new String[bcc.size()]));
		msg.setFrom(maillog.get(ml.fromAddr));
		msg.setReplyTo(maillog.get(ml.replyToAddr));
		msg.setSubject(maillog.get(ml.subject));
		msg.setText(maillog.get(ml.body));
		return msg;
	}

	@Override
	public void finishMessage(long messageId) {

		SQLUpdateClause update = queryFactory.update(ml);
		update.set(ml.mailStatus, 1);
		update.set(ml.sentAt, bizDateTime.now());
		update.set(ml.lockVersion, ml.lockVersion.add(1));
		update.where(ml.id.eq(messageId));
		long count = update.execute();
		checkState(count == 1L, "failed to update %s: id=%s, mailStatus=%s", ml.getTableName(), messageId, 1);

		long c = queryFactory.delete(mq).where(mq.mailId.eq(messageId)).execute();
		checkState(c == 1L, "failed to delete %s: mailId=%s", mq.getTableName(), messageId);
	}

	private long createMailLog(String launcherId, LocalDateTime launchedAt, String messageName,
			LocalDateTime scheduledAt, String from, String replyTo, String subject, String body) {
		SQLInsertClause insert = queryFactory.insert(ml);
		insert.set(ml.launchedBy, launcherId);
		insert.set(ml.launchedAt, launchedAt);
		insert.set(ml.mailStatus, 0);
		insert.set(ml.messageName, messageName);
		insert.set(ml.scheduledAt, scheduledAt);
		insert.set(ml.fromAddr, from);
		insert.set(ml.replyToAddr, replyTo);
		insert.set(ml.subject, subject);
		insert.set(ml.body, body);
		Long id = insert.executeWithKey(Long.class);
		checkState(
				id != null,
				"failed to create %s: launchedBy=%s, launchedAt=%s, mailStatus=%s, messageName=%s, scheduledAt=%s, fromAddr=%s, replyToAddr=%s, subject=%s, body=%s",
				ml.getTableName(), launcherId, launchedAt, 0, messageName, scheduledAt, from, replyTo, subject, body);
		return id.longValue();
	}

	private void createMailRcpt(long mailId, String rcptType, List<String> rcptAddr) {
		if (rcptAddr == null || rcptAddr.isEmpty()) {
			return;
		}
		for (String addr : rcptAddr) {
			SQLInsertClause insert = queryFactory.insert(mr);
			insert.set(mr.mailId, mailId);
			insert.set(mr.rcptType, rcptType);
			insert.set(mr.rcptAddr, addr);
			long c = insert.execute();
			checkState(c == 1L, "failed to create %s: mailId=%s, rcptType=%s, rcptAddr=%s", mr.getTableName(), mailId,
					rcptType, addr);
		}
	}

	private void createMailQueue(long mailId, LocalDateTime scheduledAt) {
		SQLInsertClause insert = queryFactory.insert(mq);
		insert.set(mq.mailId, mailId);
		insert.set(mq.scheduledAt, scheduledAt);
		long count = insert.execute();
		checkState(count == 1L, "failed to create %s: mailId=%s, scheduledAt=%s", mq.getTableName(), mailId,
				scheduledAt);
	}

}
