package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.TakeInRequest;
import fudan.se.lab2.domain.Exam;
import fudan.se.lab2.domain.Patient;
import fudan.se.lab2.repository.BedRepository;
import fudan.se.lab2.repository.ExamRepository;
import fudan.se.lab2.repository.PatientRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmergencyService {
    private PatientRepository patientRepository;
    private ExamRepository examRepository;


    @Autowired
    public EmergencyService(PatientRepository patientRepository,
                            ExamRepository examRepository

                            ) {
        this.patientRepository = patientRepository;
        this.examRepository = examRepository;

    }

    public void takeIn(TakeInRequest request) {
        Patient patient = new Patient(
                request.getPatientname(),
                request.getPatientsex(),
                request.getType(),
                3,
                3
        );
        Exam exam = new Exam(
                patient.getPatientid(),
                request.getExamresult(),
                request.getDate(),
                request.getType(),
                request.getTemperature(),
                "takein"
        );

        patient.getExams().add(exam.getExamid());
        examRepository.save(exam);
        patientRepository.save(patient);

        PublicService.changeArea();
    }

    public Iterable<Patient> allPatient(){
        return  patientRepository.findAll();
    }
}
