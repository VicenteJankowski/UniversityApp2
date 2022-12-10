package pl.admonster.UniversityApp2.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
abstract class UniversityMember {

    public UniversityMember(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    @NaturalId
    private String email;

    public void setAge(int age) throws IllegalArgumentException{
        if(age <= 18)
            throw new IllegalArgumentException("age under 19");
        this.age = age;
    }

    public void setEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailValidation = Pattern.compile(regex);
        Matcher matcher = emailValidation.matcher(email);

        if(!matcher.matches())
            throw new InvalidParameterException("wrong e-mail address");
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof UniversityMember))
            return false;

        UniversityMember castedObj = (UniversityMember) obj;

        return this.email.equals(castedObj.getEmail());
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
