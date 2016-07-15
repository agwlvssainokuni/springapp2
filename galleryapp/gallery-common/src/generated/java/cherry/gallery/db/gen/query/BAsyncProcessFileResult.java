package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessFileResult is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BAsyncProcessFileResult {

    private Long asyncId;

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private Long ngCount;

    private Long okCount;

    private Long totalCount;

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

    public Long getNgCount() {
        return ngCount;
    }

    public void setNgCount(Long ngCount) {
        this.ngCount = ngCount;
    }

    public Long getOkCount() {
        return okCount;
    }

    public void setOkCount(Long okCount) {
        this.okCount = okCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "asyncId = " + asyncId + ", createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", ngCount = " + ngCount + ", okCount = " + okCount + ", totalCount = " + totalCount + ", updatedAt = " + updatedAt;
    }

}

