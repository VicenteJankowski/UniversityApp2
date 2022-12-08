package pl.admonster.UniversityApp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
