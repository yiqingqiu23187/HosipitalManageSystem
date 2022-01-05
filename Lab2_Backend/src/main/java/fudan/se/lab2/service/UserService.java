package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.controller.response.*;

import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.Patient;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.*;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/**
 * @author LBW
 */
@Service
public class UserService {
    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;
    private PatientRepository patientRepository;
    private BedRepository bedRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       JwtTokenUtil jwtTokenUtil,
                       PatientRepository patientRepository,
                       BedRepository bedRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.patientRepository = patientRepository;
        this.bedRepository = bedRepository;
    }


    //return user
    public User login(String username, String password) throws UsernameNotFoundException, BadCredentialsException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        if (!password.equals(user.getPassword())) throw new BadCredentialsException(username);

        return user;
    }

    //return token
    public String login(String username) throws UsernameNotFoundException, BadCredentialsException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return jwtTokenUtil.generateToken(user);
    }


    public User personalInfor(Long userid){
        return userRepository.findByUserid(userid);
    }

    public void changePerson(ChangepersonRequest request){
        User user = userRepository.findByUserid(request.getUserid());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setSex(request.getSex());
        user.setTelephone(request.getTelephone());

        userRepository.save(user);
    }
}
