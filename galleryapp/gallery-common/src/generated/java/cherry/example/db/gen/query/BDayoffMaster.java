package cherry.example.db.gen.query;

import javax.annotation.Generated;

/**
 * BDayoffMaster is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BDayoffMaster {

    private java.time.LocalDateTime createdAt;

    private java.time.LocalDate dt;

    private Integer lockVersion;

    private String name;

    private String type;

    private java.time.LocalDateTime updatedAt;

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.LocalDate getDt() {
        return dt;
    }

    public void setDt(java.time.LocalDate dt) {
        this.dt = dt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "createdAt = " + createdAt + ", dt = " + dt + ", lockVersion = " + lockVersion + ", name = " + name + ", type = " + type + ", updatedAt = " + updatedAt;
    }

}

