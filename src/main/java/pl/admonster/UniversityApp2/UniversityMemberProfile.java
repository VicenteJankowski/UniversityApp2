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

    private String firstName;

    private String lastName;

    private int age;

    private String email;

}
