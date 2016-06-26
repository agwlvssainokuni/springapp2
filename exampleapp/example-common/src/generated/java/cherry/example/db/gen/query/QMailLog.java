package cherry.example.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailLog is a Querydsl query type for BMailLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMailLog extends com.querydsl.sql.RelationalPathBase<BMailLog> {

    private static final long serialVersionUID = -579852154;

    public static final QMailLog mailLog = new QMailLog("MAIL_LOG");

    public final StringPath body = createString("body");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath fromAddr = createString("fromAddr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> launchedAt = createDateTime("launchedAt", java.time.LocalDateTime.class);

    public final StringPath launchedBy = createString("launchedBy");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> mailStatus = createNumber("mailStatus", Integer.class);

    public final StringPath messageName = createString("messageName");

    public final StringPath replyToAddr = createString("replyToAddr");

    public final DateTimePath<java.time.LocalDateTime> scheduledAt = createDateTime("scheduledAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> sentAt = createDateTime("sentAt", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BMailLog> mailLogPkc = createPrimaryKey(id);

    public QMailLog(String variable) {
        super(BMailLog.class, forVariable(variable), "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public QMailLog(String variable, String schema, String table) {
        super(BMailLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailLog(Path<? extends BMailLog> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public QMailLog(PathMetadata metadata) {
        super(BMailLog.class, metadata, "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(body, ColumnMetadata.named("BODY").withIndex(11).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(13).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(fromAddr, ColumnMetadata.named("FROM_ADDR").withIndex(8).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(launchedAt, ColumnMetadata.named("LAUNCHED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(launchedBy, ColumnMetadata.named("LAUNCHED_BY").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(14).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailStatus, ColumnMetadata.named("MAIL_STATUS").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(messageName, ColumnMetadata.named("MESSAGE_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(replyToAddr, ColumnMetadata.named("REPLY_TO_ADDR").withIndex(9).ofType(Types.VARCHAR).withSize(300));
        addMetadata(scheduledAt, ColumnMetadata.named("SCHEDULED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(sentAt, ColumnMetadata.named("SENT_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(subject, ColumnMetadata.named("SUBJECT").withIndex(10).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(12).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

