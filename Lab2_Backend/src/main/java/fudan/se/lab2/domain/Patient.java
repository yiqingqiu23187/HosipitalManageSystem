package fudan.se.lab2.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long patientid;

    //这里的userid指的是病人对应的病房护士的id，并非病人自己的id
    private Long userid;
    @Column(unique = true)
    private Long bedid;

    private String patientname;
    private int patientsex;
    private int type;//0 轻症 1 重症 2 危重症 3 隔离区 4 已死亡/出院
    private int area;//0 轻症 1 重症 2 危重症 3 隔离区 4 已死亡/出院
    private int lifestate;//0 康复出院 1 在院治疗 2 病亡 3 在隔离区（没有空位仍在等待）

    private ArrayList<Long> exams = new ArrayList<>();

    public Long getBedid() {
        return bedid;
    }

    public Patient() {

    }

    public void setBedid(Long bedid) {
        this.bedid = bedid;
    }

    public Patient(String patientname, int patientsex, int type, int area, int lifestate) {
        this.patientname = patientname;
        this.patientsex = patientsex;
        this.type = type;
        this.area = area;
        this.lifestate = lifestate;
    }

    public ArrayList<Long> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Long> exams) {
        this.exams = exams;
    }

    public Long getPatientid() {
        return patientid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public int getPatientsex() {
        return patientsex;
    }

    public void setPatientsex(int patientsex) {
        this.patientsex = patientsex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getLifestate() {
        return lifestate;
    }

    public void setLifestate(int lifestate) {
        this.lifestate = lifestate;
    }


}
