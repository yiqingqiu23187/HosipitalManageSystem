package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.ChangePatientRequest;
import fudan.se.lab2.controller.request.PatientidRequest;
import fudan.se.lab2.controller.request.UseridRequest;
import fudan.se.lab2.controller.response.AllSickroomResponse;
import fudan.se.lab2.controller.response.MainnurseResponse;
import fudan.se.lab2.controller.response.MyPatientResponse;
import fudan.se.lab2.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctormainnurse")
    public ResponseEntity<?> mainNurse(@RequestBody UseridRequest request) {
        MainnurseResponse response = new MainnurseResponse();
        response.setMainnurses(doctorService.mainNurse(request.getUserid()));

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/doctorallsickroom", "/mainnurseallsickroom"})
    public ResponseEntity<?> allsickroom(@RequestBody UseridRequest request) {
        AllSickroomResponse response = new AllSickroomResponse();
        response.setSickrooms(doctorService.allSickroom(request.getUserid()));

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/doctormypatient", "/mainnursemypatient"})
    public ResponseEntity<?> myPatient(@RequestBody UseridRequest request) {

        return ResponseEntity.ok(doctorService.myPatient(request.getUserid()));
    }

    @PostMapping("/doctorchangepatientinformation")
    public ResponseEntity<?> changePatient(@RequestBody ChangePatientRequest request) {
        doctorService.changePatient(request);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/doctormygoodpatient")
    public ResponseEntity<?> myGoodPatient(@RequestBody UseridRequest request) {
        Map<String, Iterable> response = new HashMap<>();
        response.put("patients", doctorService.myGoodPatient(request.getUserid()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/doctoragree")
    public ResponseEntity<?> agree(@RequestBody PatientidRequest request) {
        doctorService.agree(request.getPatientid());
        return ResponseEntity.ok("ok");
    }
}
