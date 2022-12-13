package pl.admonster.UniversityApp2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
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
        setCourse(course);
    }

    String course;

    @ManyToMany(cascade=CascadeType.ALL)
    @JsonBackReference
    Set<Student> students;

    public Teacher addStudent(Student student) {
        students.add(student);
        //student.teachers.add(this);
        return this;
    }

    public Teacher removeStudent(Student student) {
        students.remove(student);
        //student.teachers.remove(this);
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{ " +
                super.toString() + '\'' +
                "course='" + course + '\'' +
                ", students=" + students +
                '}';
    }

}
