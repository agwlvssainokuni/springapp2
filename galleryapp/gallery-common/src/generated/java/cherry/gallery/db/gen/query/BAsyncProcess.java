package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcess is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcess {

    private String asyncStatus;

    private String asyncType;

    private java.time.LocalDateTime createdAt;

    private String description;

    private java.time.LocalDateTime finishedAt;

    private Long id;

    private java.time.LocalDateTime launchedAt;

    private String launchedBy;

    private Integer lockVersion;

    private java.time.LocalDateTime registeredAt;

    private java.time.LocalDateTime startedAt;

    private java.time.LocalDateTime updatedAt;

    public String getAsyncStatus() {
        return asyncStatus;
    }

    public void setAsyncStatus(String asyncStatus) {
        this.asyncStatus = asyncStatus;
    }

    public String getAsyncType() {
        return asyncType;
    }

    public void setAsyncType(String asyncType) {
        this.asyncType = asyncType;
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

    public java.time.LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(java.time.LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
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

    public java.time.LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(java.time.LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public java.time.LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(java.time.LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncStatus = " + asyncStatus + ", asyncType = " + asyncType + ", createdAt = " + createdAt + ", description = " + description + ", finishedAt = " + finishedAt + ", id = " + id + ", launchedAt = " + launchedAt + ", launchedBy = " + launchedBy + ", lockVersion = " + lockVersion + ", registeredAt = " + registeredAt + ", startedAt = " + startedAt + ", updatedAt = " + updatedAt;
    }

}

