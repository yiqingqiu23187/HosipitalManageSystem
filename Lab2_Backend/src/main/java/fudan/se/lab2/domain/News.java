package fudan.se.lab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long newsid;
    private int area;
    private Long patientid;
    private String patientname;
    private int originalarea;

    private boolean read = false;

    public News() {

    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public Long getNewsid() {
        return newsid;
    }

    public News(int area, Long patientid, int originalarea, boolean read) {
        this.area = area;
        this.patientid = patientid;
        this.originalarea = originalarea;
        this.read = read;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public int getOriginalarea() {
        return originalarea;
    }

    public void setOriginalarea(int originalarea) {
        this.originalarea = originalarea;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
