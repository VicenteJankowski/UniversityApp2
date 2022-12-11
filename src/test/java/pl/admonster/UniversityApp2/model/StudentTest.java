package pl.admonster.UniversityApp2.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student firstStudent = new Student("Michał", "Jankowski", 32, "jxstudiopl@gmail.com", "Inżynieria Lądowa");
    Student secondSameStudent = new Student("Michał", "Jankowski", 32, "jxstudiopl@gmail.com", "Inżynieria Lądowa");
    Student thirdSameStudent = new Student("Michał", "Jankowski", 32, "jxstudiopl@gmail.com", "Inżynieria Lądowa");

    Student firstDiffrentStudent = new Student("Marlena", "Zorzycka", 22, "marlena_zorzycka@gmail.com", "Inżynieria Środowiska");

    //Equals contract tests
    @Test
    public void equalsReflexiveTest() {
        assertTrue(firstStudent.equals(firstStudent));
    }

    @Test
    public void equalsSymmetricTest(){
        assertEquals(firstStudent.equals(secondSameStudent), secondSameStudent.equals(firstStudent));
        assertEquals(firstStudent.equals(firstDiffrentStudent), firstDiffrentStudent.equals(firstStudent));
    }

    @Test
    public void equalsTransitiveTest(){
        assertEquals(firstStudent.equals(secondSameStudent), secondSameStudent.equals(thirdSameStudent));
    }

    @Test
    public void equalsConsistentTest(){
        Set<Boolean> resultOfEquals = new HashSet<>();

        for (int i = 0; i < 100; i++)
            resultOfEquals.add(firstStudent.equals(secondSameStudent));

        assertEquals(1, resultOfEquals.size());
    }

    @Test
    public void equalsNullTest(){
        assertFalse(firstStudent.equals(null));
    }

    //hashCode contract tests
    @Test
    public void hashCodeConsistentTest(){
        Set<Integer> resultHashCode = new HashSet<>();

        for (int i = 0; i < 100; i++)
            resultHashCode.add(firstStudent.hashCode());

        assertEquals(1, resultHashCode.size());
    }

    @Test
    public void hashCodeReflexiveTest(){
        assertEquals(firstStudent.hashCode(), secondSameStudent.hashCode());
    }

    @Test
    public void hashCodeDifferenceTest(){
        assertNotEquals(firstStudent.hashCode(), firstDiffrentStudent.hashCode());
    }

    //data validation tests

    @Test
    public void eighteenYearsOldSetterTest(){
        assertThrows(IllegalArgumentException.class, () -> firstStudent.setAge(18));
    }

    @Test
    public void eighteenYearsOldConstructorTest(){
        assertThrows(IllegalArgumentException.class, () -> new Student("Jan", "Kowalski", 18, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void thirtyTwoYearsOldSetterTest(){
        assertDoesNotThrow(() -> firstStudent.setAge(32));
    }

    @Test
    public void thirtyTwoYearsOldConstructorTest(){
        assertDoesNotThrow(() -> new Student("Jan", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void emailAddreessWithoutAtSetterTest(){
        assertThrows(InvalidParameterException.class, () -> firstStudent.setEmail("randomEmailAddress.pl"));
    }

    @Test
    public void emailAddreessWithoutAtConstructorTest(){
        assertThrows(InvalidParameterException.class, () -> new Student("Jan", "Kowalski", 32, "randomEmailAddress.pl", "Mechanika"));
    }

    @Test
    public void correctEmailAddressAtSetterTest(){
        assertDoesNotThrow(() -> firstStudent.setEmail("jxstudiopl@gmail.com"));
    }

    @Test
    public void correctEmailAddressAtConstructorTest(){
        assertDoesNotThrow(() -> new Student("Jan", "Kowalski", 32, "jxstudiopl@gmail.com", "Mechanika"));
    }

    @Test
    public void twoLettersFirstNameSetterTest(){
        assertThrows(InvalidParameterException.class, () -> firstStudent.setFirstName("Mi"));
    }

    @Test
    public void twoLettersFirstNameConstructorTest(){
        assertThrows(InvalidParameterException.class, () -> new Student("Mi", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void threeLettersFirstNameSetterTest(){
        assertDoesNotThrow(() -> firstStudent.setFirstName("Leo"));
    }

    @Test
    public void threeLettersFirstNameConstructorTest(){
        assertDoesNotThrow(() -> new Student("Leo", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }
}