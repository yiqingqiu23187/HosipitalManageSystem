package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.controller.response.*;
import fudan.se.lab2.domain.User;

import fudan.se.lab2.exception.ControllerAdvisor;

import fudan.se.lab2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user;
        String token;
        try {
            user = userService.login(request.getUsername(), request.getPassword());
            token = userService.login(request.getUsername());
        } catch (UsernameNotFoundException ex) {
            return new ControllerAdvisor().handleUsernameNotFoundException(ex);
        } catch (BadCredentialsException ex) {
            return new ControllerAdvisor().handleBadCredentialsException(ex);
        }
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserDetail(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/personalinformation")
    public ResponseEntity<?> personalInformation(@RequestBody PersonalInforRequest request) {
        return ResponseEntity.ok(userService.personalInfor(request.getUserid()));
    }

    @PostMapping("/changepersonalinformation")
    public ResponseEntity<?> changeperson(@RequestBody ChangepersonRequest  request){
        userService.changePerson(request);
        return ResponseEntity.ok("ok");
    }
    /**
     * This is a function to test connectivity.
     */
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        Map<String, String> response = new HashMap<>();
        String message = "Welcome!";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}

