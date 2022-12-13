package pl.admonster.UniversityApp2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);

    Page<Student> findByTeachers_Id(Long id, Pageable pageable);

}
