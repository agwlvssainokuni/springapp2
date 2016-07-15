package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailQueue is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BMailQueue {

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private Long mailId;

    private java.time.LocalDateTime scheduledAt;

    private java.time.LocalDateTime updatedAt;

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public java.time.LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(java.time.LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", mailId = " + mailId + ", scheduledAt = " + scheduledAt + ", updatedAt = " + updatedAt;
    }

}

