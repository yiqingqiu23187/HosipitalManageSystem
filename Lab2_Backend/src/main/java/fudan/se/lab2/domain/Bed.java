package fudan.se.lab2.domain;

import javax.persistence.*;

@Entity
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long bedid;

    private int area;
    private int roomnum;
    private int bednum;
    @Column(nullable = true)
    private Long patientid = null;

    public Bed() {
    }

    public Bed(int area, int roomnum, int bednum) {
        this.area = area;
        this.roomnum = roomnum;
        this.bednum = bednum;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    public int getBednum() {
        return bednum;
    }

    public void setBednum(int bednum) {
        this.bednum = bednum;
    }

    public Long getPatientid() {
        return patientid;
    }

    public Long getBedid() {
        return bedid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }
}
