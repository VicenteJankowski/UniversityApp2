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
import java.util.Optional;

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

    @GetMapping("/students")
    public List<Student> getAllStudents() { return studentRepository.findAll(); }

    @GetMapping("/student/{firstName}/{lastName}")
    public Optional<Student> getStudentByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return studentRepository.getStudentByFirstNameAndLastName(firstName, lastName);
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

    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") final Long id, @RequestBody final Teacher teacher){
        Teacher updatedTeacher = teacherRepository.getReferenceById(id);

        if(updatedTeacher == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String updatedfirstName = teacher.getFirstName() == null ? updatedTeacher.getFirstName() : teacher.getFirstName();
        String updatedLastName = teacher.getLastName() == null ? updatedTeacher.getLastName() : teacher.getLastName();
        String updatedEmail = teacher.getEmail() == null ? updatedTeacher.getEmail() : teacher.getEmail();
        int updatedAge = teacher.getAge() <= 0 ? updatedTeacher.getAge() : teacher.getAge();
        String updatedCourse = teacher.getCourse() == null ? updatedTeacher.getCourse() : teacher.getCourse();

        updatedTeacher.setFirstName(updatedfirstName);
        updatedTeacher.setLastName(updatedLastName);
        updatedTeacher.setEmail(updatedEmail);
        updatedTeacher.setAge(updatedAge);
        updatedTeacher.setCourse(updatedCourse);

        teacherRepository.save(updatedTeacher);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") final Long id, @RequestBody final Student student){
        Student updatedStudent = studentRepository.getReferenceById(id);

        if(updatedStudent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String updatedfirstName = student.getFirstName() == null ? updatedStudent.getFirstName() : student.getFirstName();
        String updatedLastName = student.getLastName() == null ? updatedStudent.getLastName() : student.getLastName();
        String updatedEmail = student.getEmail() == null ? updatedStudent.getEmail() : student.getEmail();
        int updatedAge = student.getAge() <= 0 ? updatedStudent.getAge() : student.getAge();
        String updatedFaculty = student.getFaculty() == null ? updatedStudent.getFaculty() : student.getFaculty();

        updatedStudent.setFirstName(updatedfirstName);
        updatedStudent.setLastName(updatedLastName);
        updatedStudent.setEmail(updatedEmail);
        updatedStudent.setAge(updatedAge);
        updatedStudent.setFaculty(updatedFaculty);

        studentRepository.save(updatedStudent);

        return new ResponseEntity<>(HttpStatus.OK);
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
