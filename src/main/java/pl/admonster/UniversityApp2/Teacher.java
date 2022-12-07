package pl.admonster.UniversityApp2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name="teachers")
public class Teacher extends UniversityMemberProfile{

    String course;

    @ManyToMany
    Set<Student> assignedStudents;

}
