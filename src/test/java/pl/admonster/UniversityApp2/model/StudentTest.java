package pl.admonster.UniversityApp2.model;

import org.junit.jupiter.api.Test;

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
}