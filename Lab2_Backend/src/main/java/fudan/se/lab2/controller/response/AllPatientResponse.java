package fudan.se.lab2.controller.response;

public class AllPatientResponse {
    Iterable patients;

    public Iterable getPatients() {
        return patients;
    }

    public void setPatients(Iterable patients) {
        this.patients = patients;
    }
}
