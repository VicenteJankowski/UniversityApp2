package pl.admonster.UniversityApp2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        setFaculty(faculty);
    }

    String faculty;

    @ManyToMany(mappedBy="students")
    @JsonManagedReference
    Set<Teacher> teachers;

    public Student addTeacher(Teacher teacher) {
        teachers.add(teacher);
        //teacher.students.add(this);
        return this;
    }

    public Student removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        //teacher.students.remove(this);
        return this;
    }

    @Override
    public String toString() {
        return "Student{ " +
                super.toString() + '\'' +
                "faculty='" + faculty + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
