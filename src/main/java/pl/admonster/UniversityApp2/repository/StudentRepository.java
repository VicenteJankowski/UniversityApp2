package pl.admonster.UniversityApp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
