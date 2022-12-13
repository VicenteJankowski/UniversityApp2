package pl.admonster.UniversityApp2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.admonster.UniversityApp2.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findTeacherByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    Page<Teacher> findByStudents_Id(Long id, Pageable pageable);

}
