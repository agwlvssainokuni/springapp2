package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BNumberingMaster is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BNumberingMaster {

    private java.time.LocalDateTime createdAt;

    private Long currentValue;

    private Long id;

    private Integer lockVersion;

    private Long maxValue;

    private Long minValue;

    private String name;

    private String template;

    private java.time.LocalDateTime updatedAt;

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
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

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", currentValue = " + currentValue + ", id = " + id + ", lockVersion = " + lockVersion + ", maxValue = " + maxValue + ", minValue = " + minValue + ", name = " + name + ", template = " + template + ", updatedAt = " + updatedAt;
    }

}

