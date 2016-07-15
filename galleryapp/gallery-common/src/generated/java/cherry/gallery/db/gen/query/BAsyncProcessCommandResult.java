package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessCommandResult is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessCommandResult {

    private Long asyncId;

    private java.time.LocalDateTime createdAt;

    private Integer exitValue;

    private Long id;

    private Integer lockVersion;

    private String stderr;

    private String stdout;

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

    public Integer getExitValue() {
        return exitValue;
    }

    public void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
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

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncId = " + asyncId + ", createdAt = " + createdAt + ", exitValue = " + exitValue + ", id = " + id + ", lockVersion = " + lockVersion + ", stderr = " + stderr + ", stdout = " + stdout + ", updatedAt = " + updatedAt;
    }

}

