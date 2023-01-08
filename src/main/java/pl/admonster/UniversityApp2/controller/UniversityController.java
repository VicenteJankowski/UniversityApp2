package pl.admonster.UniversityApp2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.admonster.UniversityApp2.model.Student;
import pl.admonster.UniversityApp2.model.Teacher;
import pl.admonster.UniversityApp2.service.UniversityService;

import java.util.Map;

@Controller
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping("/")
    @SuppressWarnings("unused")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/teachers")
    @SuppressWarnings("unused")
    public ResponseEntity<String> getAllTeachers(
        Model model,
        @RequestParam(required = false) Long studentId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "2") int size,
        @RequestParam(defaultValue = "id,desc") String[] requestedSort)
        throws JsonProcessingException {
            return universityService.getAllTeachers(studentId, page, size, requestedSort);
    }

    @GetMapping("/students")
    @SuppressWarnings("unused")
    public String getAllStudents(
        Model model,
        @RequestParam(required = false) Long teacherId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "2") int size,
        @RequestParam(defaultValue = "id,desc") String[] requestedSort) {
            ResponseEntity<Map<String, Object>> response = universityService.getAllStudents(teacherId, page, size, requestedSort);
            model.addAttribute("students", response.getBody().get("students"));
            model.addAttribute("currentPage", response.getBody().get("currentPage"));
            model.addAttribute("totalItems", response.getBody().get("totalItems"));
            model.addAttribute("totalPages", response.getBody().get("totalPages"));
            return "students";
    }

    @GetMapping("/teacher/{firstName}/{lastName}")
    @SuppressWarnings("unused")
    public ResponseEntity<String> getTeacherByFirstNameAndLastName(
        @PathVariable("firstName") final String firstName,
        @PathVariable("lastName") final String lastName)
        throws JsonProcessingException {
            return universityService.getTeacherByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/student/{firstName}/{lastName}")
    @SuppressWarnings("unused")
    public ResponseEntity<String> getStudentByFirstNameAndLastName(
        @PathVariable("firstName") final String firstName,
        @PathVariable("lastName") final String lastName)
        throws JsonProcessingException {
            return universityService.getStudentByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("/teacher")
    @SuppressWarnings("unused")
    public ResponseEntity<Teacher> createTeacher (@RequestBody final Teacher teacher) {
        return universityService.createTeacher(teacher);
    }

    @PostMapping("/student")
    @SuppressWarnings("unused")
    public ResponseEntity<Student> createStudent (@RequestBody final Student student) {
        return universityService.createStudent(student);
    }

    @PutMapping("/teacher/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<Teacher> updateTeacher (@PathVariable("id") final Long id, @RequestBody final Teacher teacher){
        return universityService.updateTeacher(id, teacher);
    }

    @PutMapping("/student/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<Student> updateStudent (@PathVariable("id") final Long id, @RequestBody final Student student){
        return universityService.updateStudent(id, student);
    }

    @PutMapping("/teacher/{teacherId}/add/student/{studentId}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> addStudentToTeacher (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        return universityService.addStudentToTeacher(teacherId, studentId);
    }

    @PutMapping("/student/{studentId}/add/teacher/{teacherId}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> addTeacherToStudent (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        return universityService.addTeacherToStudent(teacherId, studentId);
    }

    @DeleteMapping("/teacher/{teacherId}/remove/student/{studentId}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> removeStudentFromTeacher (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        return universityService.removeStudentFromTeacher(teacherId, studentId);
    }

    @DeleteMapping("/student/{teacherId}/remove/teacher/{studentId}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> removeTeacherFromStudent (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        return universityService.removeTeacherFromStudent(teacherId, studentId);
    }

    @DeleteMapping("/teacher/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> deleteTeacher (@PathVariable("id") final Long id){
        return universityService.deleteTeacher(id);
    }

    @DeleteMapping("/student/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable("id") final Long id){
        return universityService.deleteStudent(id);
    }
}
