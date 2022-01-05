package fudan.se.lab2.domain;

import javax.persistence.*;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long examid;

    private Long patientid;
    private int result;
    private String date;
    private int type;
    private double temperature;
    private String symptom;

    public Exam(Long patienid, int result, String date, int type, double temperature,String symptom) {
        this.patientid = patienid;
        this.result = result;
        this.date = date;
        this.type = type;
        this.symptom = symptom;
        this.temperature = temperature;
    }

    public Exam(){}
    public Long getExamid() {
        return examid;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
