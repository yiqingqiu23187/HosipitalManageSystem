package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ChangePatientRequest;
import fudan.se.lab2.controller.request.ChangepersonRequest;
import fudan.se.lab2.controller.response.MyPatientResponse;
import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.Exam;
import fudan.se.lab2.domain.Patient;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.BedRepository;
import fudan.se.lab2.repository.ExamRepository;
import fudan.se.lab2.repository.PatientRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorService {
    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private ExamRepository examRepository;
    private BedRepository bedRepository;

    @Autowired
    public DoctorService(UserRepository userRepository,
                         PatientRepository patientRepository,
                         ExamRepository examRepository,
                         BedRepository bedRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.examRepository = examRepository;
        this.bedRepository = bedRepository;
    }

    public Iterable<User> mainNurse(Long userid) {
        User doctor = userRepository.findByUserid(userid);
        int area = doctor.getArea();

        Iterable<User> mainnurses = userRepository.findByAreaAndIdentity(area, 1);
        return mainnurses;
    }

    public Iterable<User> allSickroom(Long userid) {
        User doctor = userRepository.findByUserid(userid);
        int area = doctor.getArea();

        return userRepository.findByAreaAndIdentity(area, 3);
    }

    public MyPatientResponse myPatient(Long userid) {
        User doctor = userRepository.findByUserid(userid);
        int area = doctor.getArea();
        Iterable<Patient> patients = patientRepository.findByArea(area);
        ArrayList<Bed> beds = new ArrayList<>();
        for (Patient e : patients) {
            beds.add(bedRepository.findByBedid(e.getBedid()));
        }

        return new MyPatientResponse(patients, beds);
    }

    public void changePatient(ChangePatientRequest request) {
        Patient patient = patientRepository.findByPatientid(request.getPatientid());
        if (request.getLifestate() == 2) {
            Long sickroomid = patient.getUserid();
            User sickroom = userRepository.findByUserid(sickroomid);
            sickroom.getPatientsid().remove(patient.getPatientid());
            userRepository.save(sickroom);

            Long bedid = patient.getBedid();
            Bed bed = bedRepository.findByBedid(bedid);
            bed.setPatientid(null);
            bedRepository.save(bed);

            patient.setBedid(null);
            patient.setUserid(null);
            patient.setType(request.getType());
            patient.setArea(4);
            patient.setLifestate(2);
            patientRepository.save(patient);

            PublicService.changeArea();
        } else {
            patient.setLifestate(request.getLifestate());
            patient.setType(request.getType());

            patientRepository.save(patient);

            PublicService.changeArea();
        }
    }

    public Iterable<Patient> myGoodPatient(Long userid) {
        Iterable<Patient> patients = patientRepository.findByArea(userRepository.findByUserid(userid).getArea());
        ArrayList<Patient> result = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getType() != 0) continue;
            Iterable<Exam> exams = examRepository.findByPatientid(patient.getPatientid());
            int count = 0;
            for (Exam exam : exams) {
                if (exam.getTemperature() < 37.3 && exam.getResult() == 1) {
                    count++;
                } else count = 0;
            }
            if (count >= 3) {
                result.add(patient);
            }
        }

        return result;
    }

    public void agree(Long patientid) {
        Patient patient = patientRepository.findByPatientid(patientid);
        patient.setLifestate(0);
        patient.setType(0);
        patient.setArea(4);
        //释放资源
        Long sickroomid = patient.getUserid();
        User sickroom = userRepository.findByUserid(sickroomid);
        sickroom.getPatientsid().remove(patientid);
        userRepository.save(sickroom);

        Long bedid = patient.getBedid();
        Bed bed = bedRepository.findByBedid(bedid);
        bed.setPatientid(null);
        bedRepository.save(bed);

        patient.setBedid(null);
        patient.setUserid(null);
        patientRepository.save(patient);

        PublicService.changeArea();
    }
}
