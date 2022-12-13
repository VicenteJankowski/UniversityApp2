package pl.admonster.UniversityApp2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findStudentByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    Page<Student> findByTeachers_Id(Long id, Pageable pageable);

}
