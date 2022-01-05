package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.TakeInRequest;
import fudan.se.lab2.controller.response.AllPatientResponse;
import fudan.se.lab2.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmergencyController {
    private EmergencyService emergencyService;

    @Autowired
    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/emergencytakein")
    public ResponseEntity<?> takeIn(@RequestBody TakeInRequest request) {
        emergencyService.takeIn(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/emergencyallpatient")
    public ResponseEntity<?> allpatient() {
        AllPatientResponse response = new AllPatientResponse();
        response.setPatients(emergencyService.allPatient());
        return ResponseEntity.ok(response);
    }
}
