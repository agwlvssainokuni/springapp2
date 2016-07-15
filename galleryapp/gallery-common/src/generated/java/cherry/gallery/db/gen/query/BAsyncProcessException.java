package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessException is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessException {

    private Long asyncId;

    private java.time.LocalDateTime createdAt;

    private String exception;

    private Long id;

    private Integer lockVersion;

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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
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

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncId = " + asyncId + ", createdAt = " + createdAt + ", exception = " + exception + ", id = " + id + ", lockVersion = " + lockVersion + ", updatedAt = " + updatedAt;
    }

}

