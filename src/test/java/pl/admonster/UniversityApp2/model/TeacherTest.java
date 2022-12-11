package pl.admonster.UniversityApp2.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    Teacher firstTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");
    Teacher secondSameTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");
    Teacher thirdSameTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");

    Teacher firstDiffrentTeacher = new Teacher("Mirosław", "Rzeczycki", 58, "miroslaw.rzeczycki76@yahoo.com", "Konstrukcje stalowe I");

    //Equals contract tests
    @Test
    public void equalsReflexiveTest() {
        assertTrue(firstTeacher.equals(firstTeacher));
    }

    @Test
    public void equalsSymmetricTest(){
        assertEquals(firstTeacher.equals(secondSameTeacher), secondSameTeacher.equals(firstTeacher));
        assertEquals(firstTeacher.equals(firstDiffrentTeacher), firstDiffrentTeacher.equals(firstTeacher));
    }

    @Test
    public void equalsTransitiveTest(){
        assertEquals(firstTeacher.equals(secondSameTeacher), secondSameTeacher.equals(thirdSameTeacher));
    }

    @Test
    public void equalsConsistentTest(){
        Set<Boolean> resultOfEquals = new HashSet<>();

        for (int i = 0; i < 100; i++)
            resultOfEquals.add(firstTeacher.equals(secondSameTeacher));

        assertEquals(1, resultOfEquals.size());
    }

    @Test
    public void equalsNullTest(){
        assertFalse(firstTeacher.equals(null));
    }


    //hashCode contract tests
    @Test
    public void hashCodeConsistentTest(){
        Set<Integer> resultHashCode = new HashSet<>();

        for (int i = 0; i < 100; i++)
            resultHashCode.add(firstTeacher.hashCode());

        assertEquals(1, resultHashCode.size());
    }

    @Test
    public void hashCodeReflexiveTest(){
        assertEquals(firstTeacher.hashCode(), secondSameTeacher.hashCode());
    }

    @Test
    public void hashCodeDifferenceTest(){
        assertNotEquals(firstTeacher.hashCode(), firstDiffrentTeacher.hashCode());
    }

    //data validation tests

    @Test
    public void eighteenYearsOldSetterTest(){
        assertThrows(IllegalArgumentException.class, () -> firstTeacher.setAge(18));
    }

    @Test
    public void eighteenYearsOldConstructorTest(){
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Jan", "Kowalski", 18, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void thirtyTwoYearsOldSetterTest(){
        assertDoesNotThrow(() -> firstTeacher.setAge(32));
    }

    @Test
    public void thirtyTwoYearsOldConstructorTest(){
        assertDoesNotThrow(() -> new Teacher("Jan", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void emailAddreessWithoutAtSetterTest(){
        assertThrows(InvalidParameterException.class, () -> firstTeacher.setEmail("randomEmailAddress.pl"));
    }

    @Test
    public void emailAddreessWithoutAtConstructorTest(){
        assertThrows(InvalidParameterException.class, () -> new Teacher("Jan", "Kowalski", 32, "randomEmailAddress.pl", "Mechanika"));
    }

    @Test
    public void correctEmailAddressAtSetterTest(){
        assertDoesNotThrow(() -> firstTeacher.setEmail("jxstudiopl@gmail.com"));
    }

    @Test
    public void correctEmailAddressAtConstructorTest(){
        assertDoesNotThrow(() -> new Teacher("Jan", "Kowalski", 32, "jxstudiopl@gmail.com", "Mechanika"));
    }

    @Test
    public void twoLettersFirstNameSetterTest(){
        assertThrows(InvalidParameterException.class, () -> firstTeacher.setFirstName("Mi"));
    }

    @Test
    public void twoLettersFirstNameConstructorTest(){
        assertThrows(InvalidParameterException.class, () -> new Teacher("Mi", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }

    @Test
    public void threeLettersFirstNameSetterTest(){
        assertDoesNotThrow(() -> firstTeacher.setFirstName("Leo"));
    }

    @Test
    public void threeLettersFirstNameConstructorTest(){
        assertDoesNotThrow(() -> new Teacher("Leo", "Kowalski", 32, "jankowalski@gmail.com", "Mechanika"));
    }
}