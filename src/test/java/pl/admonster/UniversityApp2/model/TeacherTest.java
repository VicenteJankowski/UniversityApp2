package pl.admonster.UniversityApp2.model;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    Teacher firstTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");
    Teacher secondSameTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");
    Teacher thirdSameTeacher = new Teacher("Jan", "Kowalski", 38, "jankowalski@o2.pl", "Mechanika budowli");

    Teacher firstDiffrentTeacher = new Teacher("Mirosław", "Rzeczycki", 58, "miroslaw.rzeczycki76", "Konstrukcje stalowe I");

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

    @RepeatedTest(10)
    public void equalsConsistentTest(){
        assertEquals(firstTeacher.equals(secondSameTeacher), secondSameTeacher.equals(firstTeacher));
        assertEquals(firstTeacher.equals(firstDiffrentTeacher), firstDiffrentTeacher.equals(firstTeacher));
    }

    @Test
    public void equalsNullTest(){
        assertFalse(firstTeacher.equals(null));
    }

}