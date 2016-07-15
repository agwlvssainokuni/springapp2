package cherry.fundamental.db.gen.query;

import javax.annotation.Generated;

/**
 * BVerifyDatetime is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BVerifyDatetime {

    private java.time.LocalDate dt;

    private java.time.LocalDateTime dtm;

    private Long id;

    private java.time.LocalTime tm;

    public java.time.LocalDate getDt() {
        return dt;
    }

    public void setDt(java.time.LocalDate dt) {
        this.dt = dt;
    }

    public java.time.LocalDateTime getDtm() {
        return dtm;
    }

    public void setDtm(java.time.LocalDateTime dtm) {
        this.dtm = dtm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.time.LocalTime getTm() {
        return tm;
    }

    public void setTm(java.time.LocalTime tm) {
        this.tm = tm;
    }

    @Override
    public String toString() {
         return "dt = " + dt + ", dtm = " + dtm + ", id = " + id + ", tm = " + tm;
    }

}

