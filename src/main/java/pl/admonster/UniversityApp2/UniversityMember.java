package pl.admonster.UniversityApp2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
abstract class UniversityMember {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String subject;


}
