package pl.admonster.UniversityApp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student extends UniversityMember {

    public Student(String firstName, String lastName, int age, String email, String faculty) {
        super(firstName, lastName, age, email);
        this.faculty = faculty;
    }

    private String faculty;

    @ManyToMany(mappedBy="assignedStudents")
    Set<Teacher> supervisorTeachers;


    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        return super.equals(obj);
    }

}
