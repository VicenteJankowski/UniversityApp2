package pl.admonster.UniversityApp2;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends UniversityMemberProfile{

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, int age, String email, String faculty) {
        super(firstName, lastName, age, email);
        this.faculty = faculty;
    }

    private String faculty;

    //@ManyToMany(mappedBy="assignedStudents")
    //Set<Teacher> supervisorTeachers;


    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
