package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.RecordRequest;
import fudan.se.lab2.controller.request.UseridRequest;
import fudan.se.lab2.controller.response.AllPatientResponse;
import fudan.se.lab2.service.SickroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SickroomController {
    private SickroomService sickroomService;

    @Autowired
    public SickroomController(SickroomService sickroomService) {
        this.sickroomService = sickroomService;
    }

    @PostMapping(value = {"/sickroomrecord", "doctorrecord"})
    public ResponseEntity<?> record(@RequestBody RecordRequest request) {
        sickroomService.record(request);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/sickroommypatient")
    public ResponseEntity<?> myPatient(@RequestBody UseridRequest request) {
        return ResponseEntity.ok(sickroomService.myPatient(request.getUserid()));
    }
}
