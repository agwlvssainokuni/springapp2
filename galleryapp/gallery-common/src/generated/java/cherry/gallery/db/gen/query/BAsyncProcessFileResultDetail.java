package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessFileResultDetail is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessFileResultDetail {

    private Long asyncId;

    private java.time.LocalDateTime createdAt;

    private String description;

    private Long id;

    private Integer lockVersion;

    private Long recordNumber;

    private java.time.LocalDateTime updatedAt;

    public Long getAsyncId() {
        return asyncId;
    }

    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Long recordNumber) {
        this.recordNumber = recordNumber;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncId = " + asyncId + ", createdAt = " + createdAt + ", description = " + description + ", id = " + id + ", lockVersion = " + lockVersion + ", recordNumber = " + recordNumber + ", updatedAt = " + updatedAt;
    }

}

