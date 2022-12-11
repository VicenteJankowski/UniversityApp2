package pl.admonster.UniversityApp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="teachers")
@Setter
@Getter
@NoArgsConstructor
public class Teacher extends UniversityMember {

    public Teacher(String firstName, String lastName, int age, String email, String course) {
        super(firstName, lastName, age, email);
        this.course = course;
    }

    private String course;

    @ManyToMany
    Set<Student> assignedStudents;

    @Override
    public String toString() {
        return "Teacher{ " +
                super.toString() + '\'' +
                "course='" + course + '\'' +
                ", assignedStudents=" + assignedStudents +
                '}';
    }

}
