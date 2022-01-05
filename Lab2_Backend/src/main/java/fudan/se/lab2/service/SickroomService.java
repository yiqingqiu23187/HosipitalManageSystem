package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.RecordRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class SickroomService {
    private PatientRepository patientRepository;
    private ExamRepository examRepository;
    private UserRepository userRepository;
    private BedRepository bedRepository;

    @Autowired
    public SickroomService(PatientRepository patientRepository,
                           ExamRepository examRepository,
                           UserRepository userRepository,
                           BedRepository bedRepository) {
        this.patientRepository = patientRepository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
        this.bedRepository = bedRepository;
    }

    public void record(RecordRequest request) {
        Patient patient = patientRepository.findByPatientid(request.getPatientid());

        Exam exam = new Exam(
                request.getPatientid(),
                request.getExamresult(),
                request.getDate(),
                request.getType(),
                request.getTemperature(),
                request.getSymptom());

        patient.getExams().add(exam.getExamid());
        patient.setType(request.getType());
        patientRepository.save(patient);
        examRepository.save(exam);

        PublicService.changeArea();
    }

    public MyPatientResponse myPatient(Long userid) {
        User user = userRepository.findByUserid(userid);
        ArrayList<Long> patientsid =  user.getPatientsid();
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        for (Long e:patientsid){
            patients.add(patientRepository.findByPatientid(e));
        }

        for (Patient e:patients){
            beds.add(bedRepository.findByBedid(e.getBedid()));
        }

        return new MyPatientResponse(patients,beds);
    }
}
