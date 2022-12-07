package pl.admonster.UniversityApp2;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
abstract class UniversityMemberProfile {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

}
