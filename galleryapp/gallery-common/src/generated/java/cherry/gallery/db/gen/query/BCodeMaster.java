package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BCodeMaster is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BCodeMaster {

    private java.time.LocalDateTime createdAt;

    private Long id;

    private String label;

    private Integer lockVersion;

    private String name;

    private Integer sortOrder;

    private java.time.LocalDateTime updatedAt;

    private String value;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", id = " + id + ", label = " + label + ", lockVersion = " + lockVersion + ", name = " + name + ", sortOrder = " + sortOrder + ", updatedAt = " + updatedAt + ", value = " + value;
    }

}

