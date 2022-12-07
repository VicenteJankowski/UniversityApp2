package pl.admonster.UniversityApp2;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends UniversityMemberProfile{

    String faculty;

}
