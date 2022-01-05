package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.AddSickroomRequest;
import fudan.se.lab2.controller.response.MyPatientResponse;
import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.News;
import fudan.se.lab2.domain.Patient;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.BedRepository;
import fudan.se.lab2.repository.NewsRepository;
import fudan.se.lab2.repository.PatientRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainnurseService {
    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private BedRepository bedRepository;
    private NewsRepository newsRepository;

    @Autowired
    public MainnurseService(UserRepository userRepository,
                            PatientRepository patientRepository,
                            BedRepository bedRepository,
                            NewsRepository newsRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.bedRepository = bedRepository;
        this.newsRepository = newsRepository;
    }

    public void addSickroom(AddSickroomRequest request) {
        User mainnurse = userRepository.findByUserid(request.getUserid());
        User sickroom = new User(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getSex(),
                mainnurse.getArea(),
                3,
                request.getTelephone()
        );

        userRepository.save(sickroom);

        PublicService.changeArea();
    }

    public boolean deleteSickroom(Long userid) {
        User sickroom = userRepository.findByUserid(userid);
        if (sickroom.getPatientsid() == null || sickroom.getPatientsid().size() == 0) {
            userRepository.delete(sickroom);
            return true;
        } else return false;
    }

    public MyPatientResponse bed(Long userid) {
        User mainnurse = userRepository.findByUserid(userid);
        Iterable<Bed> beds = bedRepository.findByArea(mainnurse.getArea());
        ArrayList<Patient> patients = new ArrayList<>();
        for (Bed bed : beds) {
            patients.add(patientRepository.findByPatientid(bed.getPatientid()));
        }

        return new MyPatientResponse(patients, beds);
    }

    public Iterable<News> newPatient(Long userid){
        return newsRepository.findByArea(userRepository.findByUserid(userid).getArea());
    }

    public void read(Long newsid){
        News news = newsRepository.findByNewsid(newsid);
        newsRepository.delete(news);
    }
}
