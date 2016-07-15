package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessCommandArg is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessCommandArg {

    private String argument;

    private Long asyncId;

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private java.time.LocalDateTime updatedAt;

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

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
         return "argument = " + argument + ", asyncId = " + asyncId + ", createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", updatedAt = " + updatedAt;
    }

}

