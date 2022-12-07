package pl.admonster.UniversityApp2;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends UniversityMemberProfile{

    String faculty;

    @ManyToMany(mappedBy="assignedStudents")
    Set<Teacher> supervisorTeachers;

}
