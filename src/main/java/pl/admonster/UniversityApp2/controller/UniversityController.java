package pl.admonster.UniversityApp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.admonster.UniversityApp2.model.Student;
import pl.admonster.UniversityApp2.repository.StudentRepository;
import pl.admonster.UniversityApp2.model.Teacher;
import pl.admonster.UniversityApp2.repository.TeacherRepository;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/test")
    public String runTest(){
        return "Hello Vicente!";
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody final Teacher teacher) {
        List<Teacher> existingTeachers = teacherRepository.findAll();

        if(existingTeachers.contains(teacher))
            return ResponseEntity.unprocessableEntity().build();
        teacherRepository.save(teacher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody final Student student) {
        List<Student> existingStudents = studentRepository.findAll();

        if(existingStudents.contains(student))
            return ResponseEntity.unprocessableEntity().build();
        studentRepository.save(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable("id") final Long id){
        teacherRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") final Long id){
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
