package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {
    Iterable<Exam> findByPatientid(Long patientid);
}
