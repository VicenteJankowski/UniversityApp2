package pl.admonster.UniversityApp2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="nauczyciele")
public class Teacher extends UniversityMemberProfile{

    @Column(name="przedmiot")
    String course;

}
