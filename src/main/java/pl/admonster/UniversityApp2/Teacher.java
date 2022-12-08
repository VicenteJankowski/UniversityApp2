package pl.admonster.UniversityApp2;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name="teachers")
public class Teacher extends UniversityMemberProfile{

    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, int age, String email, String course) {
        super(firstName, lastName, age, email);
        this.course = course;
    }

    private String course;

    //@ManyToMany
    //Set<Student> assignedStudents;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
