package fudan.se.lab2.controller.request;

public class ChangePatientRequest {
    Long patientid;
    int type;
    int lifestate;

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
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
}
