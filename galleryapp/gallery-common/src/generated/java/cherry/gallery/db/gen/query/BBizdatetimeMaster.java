package cherry.gallery.db.gen.query;

import javax.annotation.Generated;

/**
 * BBizdatetimeMaster is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BBizdatetimeMaster {

    private java.time.LocalDate bizdate;

    private java.time.LocalDateTime createdAt;

    private Long id;

    private Integer lockVersion;

    private Integer offsetDay;

    private Integer offsetHour;

    private Integer offsetMinute;

    private Integer offsetSecond;

    private java.time.LocalDateTime updatedAt;

    public java.time.LocalDate getBizdate() {
        return bizdate;
    }

    public void setBizdate(java.time.LocalDate bizdate) {
        this.bizdate = bizdate;
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

    public Integer getOffsetDay() {
        return offsetDay;
    }

    public void setOffsetDay(Integer offsetDay) {
        this.offsetDay = offsetDay;
    }

    public Integer getOffsetHour() {
        return offsetHour;
    }

    public void setOffsetHour(Integer offsetHour) {
        this.offsetHour = offsetHour;
    }

    public Integer getOffsetMinute() {
        return offsetMinute;
    }

    public void setOffsetMinute(Integer offsetMinute) {
        this.offsetMinute = offsetMinute;
    }

    public Integer getOffsetSecond() {
        return offsetSecond;
    }

    public void setOffsetSecond(Integer offsetSecond) {
        this.offsetSecond = offsetSecond;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
         return "bizdate = " + bizdate + ", createdAt = " + createdAt + ", id = " + id + ", lockVersion = " + lockVersion + ", offsetDay = " + offsetDay + ", offsetHour = " + offsetHour + ", offsetMinute = " + offsetMinute + ", offsetSecond = " + offsetSecond + ", updatedAt = " + updatedAt;
    }

}

