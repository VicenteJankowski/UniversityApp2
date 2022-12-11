package pl.admonster.UniversityApp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> getTeacherByFirstNameAndLastName(String firstName, String lastName);
}
