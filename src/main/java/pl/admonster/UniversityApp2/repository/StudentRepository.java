package pl.admonster.UniversityApp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> getStudentByFirstNameAndLastName(String firstName, String lastName);

}
