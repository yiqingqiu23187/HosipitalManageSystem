package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.AddSickroomRequest;
import fudan.se.lab2.controller.request.ReadRequest;
import fudan.se.lab2.controller.request.UseridRequest;
import fudan.se.lab2.domain.News;
import fudan.se.lab2.service.MainnurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainnurseController {
    private MainnurseService mainnurseService;

    @Autowired
    public MainnurseController(MainnurseService mainnurseService) {
        this.mainnurseService = mainnurseService;
    }

    @PostMapping("/mainnurseaddsickroom")
    public ResponseEntity<?> addSickroom(@RequestBody AddSickroomRequest request) {
        mainnurseService.addSickroom(request);
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/mainnursedeletesickroom")
    public ResponseEntity<?> deleteSickroom(@RequestBody UseridRequest request) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("ok", mainnurseService.deleteSickroom(request.getUserid()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mainnursebed")
    public ResponseEntity<?> bed(@RequestBody UseridRequest request) {
        return ResponseEntity.ok(mainnurseService.bed(request.getUserid()));
    }

    @PostMapping("/mainnurseread")
    public ResponseEntity<?> read(@RequestBody ReadRequest request){
        mainnurseService.read(request.getNewsid());
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/mainnursenewpatient")
    public ResponseEntity<?> newPatient(@RequestBody UseridRequest request){
        HashMap<String, Iterable> response = new HashMap<>();
        response.put("news",mainnurseService.newPatient(request.getUserid()));
        return ResponseEntity.ok(response);
    }
}
