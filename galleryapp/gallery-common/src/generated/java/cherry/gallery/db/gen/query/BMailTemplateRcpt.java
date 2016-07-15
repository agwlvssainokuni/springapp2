package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailTemplateRcpt is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BMailTemplateRcpt {

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private String rcptAddr;

    private String rcptType;

    private Long templateId;

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

    public String getRcptAddr() {
        return rcptAddr;
    }

    public void setRcptAddr(String rcptAddr) {
        this.rcptAddr = rcptAddr;
    }

    public String getRcptType() {
        return rcptType;
    }

    public void setRcptType(String rcptType) {
        this.rcptType = rcptType;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", rcptAddr = " + rcptAddr + ", rcptType = " + rcptType + ", templateId = " + templateId + ", updatedAt = " + updatedAt;
    }

}

