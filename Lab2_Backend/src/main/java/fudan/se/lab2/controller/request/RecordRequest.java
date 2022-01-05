package fudan.se.lab2.controller.request;

public class RecordRequest {
    Long patientid;
    String date;
    double temperature;
    String symptom;
    int type;
    int lifestate;
    int examresult;

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLifestate() {
        return lifestate;
    }

    public void setLifestate(int lifestate) {
        this.lifestate = lifestate;
    }

    public int getExamresult() {
        return examresult;
    }

    public void setExamresult(int examresult) {
        this.examresult = examresult;
    }
}
