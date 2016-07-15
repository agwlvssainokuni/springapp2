package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessCommand is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessCommand {

    private Long asyncId;

    private String command;

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private java.time.LocalDateTime updatedAt;

    public Long getAsyncId() {
        return asyncId;
    }

    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

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

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncId = " + asyncId + ", command = " + command + ", createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", updatedAt = " + updatedAt;
    }

}

