package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailLog is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BMailLog {

    private String body;

    private java.time.LocalDateTime createdAt;

    private String fromAddr;

    private Long id;

    private java.time.LocalDateTime launchedAt;

    private String launchedBy;

    private Integer lockVersion;

    private Integer mailStatus;

    private String messageName;

    private String replyToAddr;

    private java.time.LocalDateTime scheduledAt;

    private java.time.LocalDateTime sentAt;

    private String subject;

    private java.time.LocalDateTime updatedAt;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.time.LocalDateTime getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(java.time.LocalDateTime launchedAt) {
        this.launchedAt = launchedAt;
    }

    public String getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(String launchedBy) {
        this.launchedBy = launchedBy;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getReplyToAddr() {
        return replyToAddr;
    }

    public void setReplyToAddr(String replyToAddr) {
        this.replyToAddr = replyToAddr;
    }

    public java.time.LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(java.time.LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public java.time.LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(java.time.LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "body = " + body + ", createdAt = " + createdAt + ", fromAddr = " + fromAddr + ", id = " + id + ", launchedAt = " + launchedAt + ", launchedBy = " + launchedBy + ", lockVersion = " + lockVersion + ", mailStatus = " + mailStatus + ", messageName = " + messageName + ", replyToAddr = " + replyToAddr + ", scheduledAt = " + scheduledAt + ", sentAt = " + sentAt + ", subject = " + subject + ", updatedAt = " + updatedAt;
    }

}

