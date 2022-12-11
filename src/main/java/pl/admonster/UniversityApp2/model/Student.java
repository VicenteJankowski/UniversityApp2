package pl.admonster.UniversityApp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
public class Student extends UniversityMember {

    public Student(String firstName, String lastName, int age, String email, String faculty) {
        super(firstName, lastName, age, email);
        this.faculty = faculty;
    }

    private String faculty;

    @ManyToMany(mappedBy="assignedStudents")
    Set<Teacher> supervisorTeachers;

    @Override
    public String toString() {
        return "Student{ " +
                super.toString() + '\'' +
                "faculty='" + faculty + '\'' +
                ", supervisorTeachers=" + supervisorTeachers +
                '}';
    }
}
