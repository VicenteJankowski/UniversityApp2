package pl.admonster.UniversityApp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="teachers")
@NoArgsConstructor
public class Teacher extends UniversityMember {

    public Teacher(String firstName, String lastName, int age, String email, String course) {
        super(firstName, lastName, age, email);
        this.course = course;
    }

    private String course;

    @ManyToMany
    Set<Student> assignedStudents;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teacher{ " +
                super.toString() + '\'' +
                "course='" + course + '\'' +
                ", assignedStudents=" + assignedStudents +
                '}';
    }

}
