package fudan.se.lab2.service;

import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.News;
import fudan.se.lab2.domain.Patient;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.BedRepository;
import fudan.se.lab2.repository.NewsRepository;
import fudan.se.lab2.repository.PatientRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class PublicService {

    private static UserRepository userRepository;
    private static NewsRepository newsRepository;
    private static PatientRepository patientRepository;

    private static BedRepository bedRepository;

    @Autowired
    public  void setNewsRepository(NewsRepository newsRepository) {
        PublicService.newsRepository = newsRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        PublicService.userRepository = userRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        PublicService.patientRepository = patientRepository;
    }

    @Autowired
    public void setBedRepository(BedRepository bedRepository) {
        PublicService.bedRepository = bedRepository;
    }


    //系统自动分配病床和护士算法
    public static void changeArea() {
        boolean changed = true;

        while (changed) {
            changed = false;//一直迭代直到床位不可调整
            //根据要求优先分配隔离区的病人
            Iterable<Patient> firstpatients = patientRepository.findByArea(3);
            for (Patient patient : firstpatients) {
                //找到空的床
                Iterator<Bed> beds = bedRepository.findByArea(patient.getType()).iterator();
                Bed bed = null;
                while (beds.hasNext()) {
                    Bed temp = beds.next();
                    if (temp.getPatientid() == null) {
                        bed = temp;
                        break;
                    }
                }
                if (bed == null) continue;

                //找到空闲的病房护士
                Iterator<User> sickrooms = userRepository.findByAreaAndIdentity(patient.getType(), 3).iterator();
                User sickroom = null;
                int maxnum = 0;
                switch (patient.getType()) {
                    case 0:
                        maxnum = 3;
                        break;
                    case 1:
                        maxnum = 2;
                        break;
                    case 2:
                        maxnum = 1;
                        break;
                }
                while (sickrooms.hasNext()) {
                    User temp = sickrooms.next();
                    ArrayList<Long> patientsid = temp.getPatientsid();
                    if (patientsid.size() < maxnum) {
                        sickroom = temp;
                        break;
                    }
                }
                if (sickroom == null) continue;

                News news = new News();
                news.setPatientid(patient.getPatientid());
                news.setOriginalarea(patient.getArea());
                news.setArea(patient.getType());
                newsRepository.save(news);

                //开始转区域、分配病床、分配护士
                patient.setArea(patient.getType());
                patient.setBedid(bed.getBedid());
                patient.setUserid(sickroom.getUserid());
                patient.setLifestate(1);
                patientRepository.save(patient);

                bed.setPatientid(patient.getPatientid());
                bedRepository.save(bed);

                sickroom.getPatientsid().add(patient.getPatientid());
                userRepository.save(sickroom);

                changed = true;
            }

            Iterable<Patient> secondpatients = patientRepository.findAll();
            for (Patient patient : secondpatients) {
                if (patient.getArea() == 4 || patient.getType() == 4) continue;
                if (patient.getType() == patient.getArea()) continue;
                //找到空的床
                Iterator<Bed> beds = bedRepository.findByArea(patient.getType()).iterator();
                Bed bed = null;
                while (beds.hasNext()) {
                    Bed temp = beds.next();
                    if (temp.getPatientid() == null) {
                        bed = temp;
                        break;
                    }
                }
                if (bed == null) continue;

                //找到空闲的病房护士
                Iterator<User> sickrooms = userRepository.findByAreaAndIdentity(patient.getType(), 3).iterator();
                User sickroom = null;
                int maxnum = 0;
                switch (patient.getType()) {
                    case 0:
                        maxnum = 3;
                        break;
                    case 1:
                        maxnum = 2;
                        break;
                    case 2:
                        maxnum = 1;
                        break;
                    default:
                        continue;
                }
                while (sickrooms.hasNext()) {
                    User temp = sickrooms.next();
                    ArrayList<Long> patientsid = temp.getPatientsid();
                    if (patientsid.size() < maxnum) {
                        sickroom = temp;
                        break;
                    }
                }
                if (sickroom == null) continue;


                News news = new News();
                news.setPatientid(patient.getPatientid());
                news.setOriginalarea(patient.getArea());
                news.setArea(patient.getType());
                newsRepository.save(news);

                //开始转区域、分配病床、分配护士
                Bed originalBed = bedRepository.findByBedid(patient.getBedid());
                originalBed.setPatientid(null);
                bedRepository.save(originalBed);

                User originalSickroom = userRepository.findByUserid(patient.getUserid());
                originalSickroom.getPatientsid().removeIf(i -> i.equals(patient.getPatientid()));
                userRepository.save(originalSickroom);

                patient.setArea(patient.getType());
                patient.setBedid(bed.getBedid());
                patient.setUserid(sickroom.getUserid());
                patient.setLifestate(1);
                patientRepository.save(patient);

                bed.setPatientid(patient.getPatientid());
                bedRepository.save(bed);

                sickroom.getPatientsid().add(patient.getPatientid());
                userRepository.save(sickroom);

                changed = true;
            }
        }

    }
}
