package fudan.se.lab2.controller.request;

public class TakeInRequest {
    String patientname;
    int patientsex;
    int examresult;
    String date;
    int type;
    double temperature;

    public String getPatientname() {
        return patientname;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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

    public int getExamresult() {
        return examresult;
    }

    public void setExamresult(int examresult) {
        this.examresult = examresult;
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
}
