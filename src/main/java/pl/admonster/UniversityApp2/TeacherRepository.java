package pl.admonster.UniversityApp2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<UniversityMemberProfile, Long> {


}
