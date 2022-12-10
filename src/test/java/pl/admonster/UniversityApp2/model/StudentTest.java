package pl.admonster.UniversityApp2.model;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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

    @RepeatedTest(10)
    public void equalsConsistentTest(){
        assertEquals(firstStudent.equals(secondSameStudent), secondSameStudent.equals(firstStudent));
        assertEquals(firstStudent.equals(firstDiffrentStudent), firstDiffrentStudent.equals(firstStudent));
    }

    @Test
    public void equalsNullTest(){
        assertFalse(firstStudent.equals(null));
    }
}