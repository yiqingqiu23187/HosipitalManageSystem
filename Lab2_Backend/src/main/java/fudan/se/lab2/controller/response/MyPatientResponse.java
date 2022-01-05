package fudan.se.lab2.controller.response;

import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.Patient;

public class MyPatientResponse {
    Iterable<Patient> patients;
    Iterable<Bed> beds;

    public MyPatientResponse(Iterable<Patient> patients, Iterable<Bed> beds) {
        this.patients = patients;
        this.beds = beds;
    }

    public Iterable<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Iterable<Patient> patients) {
        this.patients = patients;
    }

    public Iterable<Bed> getBeds() {
        return beds;
    }

    public void setBeds(Iterable<Bed> beds) {
        this.beds = beds;
    }
}
