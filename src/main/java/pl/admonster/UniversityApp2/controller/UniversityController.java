package pl.admonster.UniversityApp2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.admonster.UniversityApp2.model.Student;
import pl.admonster.UniversityApp2.repository.StudentRepository;
import pl.admonster.UniversityApp2.model.Teacher;
import pl.admonster.UniversityApp2.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ObjectMapper objectMapper;

    private List<Order> getOrder(String[] requestedSort) {
        List<Order> orders = new ArrayList<>();
        if (requestedSort[0].contains(",")) {
            for (String sortOrder : requestedSort) {
                String[] splittedRequestedSort = sortOrder.split(",");
                orders.add(new Order(splittedRequestedSort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, splittedRequestedSort[0]));
            }
        }
        else {
            orders.add(new Order(requestedSort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, requestedSort[0]));
        }
        return orders;
    }

    @GetMapping("/teachers")
    public ResponseEntity getAllTeachers(
            @RequestParam(required = false) Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,desc") String[] requestedSort)
            throws JsonProcessingException {

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));

        Page<Teacher> singlePage = teacherRepository.findAll(pagingSort);

        if (studentId != null) {
            singlePage = teacherRepository.findByStudents_Id(studentId, pagingSort);
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(singlePage.getContent()));
    }

    @GetMapping("/students")
    public ResponseEntity getAllStudents(
            @RequestParam(required = false) Long teacherId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,desc") String[] requestedSort)
            throws JsonProcessingException {

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));
        Page<Student> singlePage = studentRepository.findAll(pagingSort);

        if (teacherId != null) {
            singlePage = studentRepository.findByTeachers_Id(teacherId, pagingSort);
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(singlePage.getContent()));
    }

    @GetMapping("/teacher/{firstName}/{lastName}")
    public ResponseEntity getTeacherByFirstNameAndLastName(
            @PathVariable("firstName") final String firstName,
            @PathVariable("lastName") final String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,desc") String[] requestedSort)
            throws JsonProcessingException {
        Pageable pageSorting = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));
        Page<Teacher> foundTeacher = teacherRepository.findTeacherByFirstNameAndLastName(firstName, lastName, pageSorting);
        if (foundTeacher.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(objectMapper.writeValueAsString(foundTeacher.getContent()));
    }

    @GetMapping("/student/{firstName}/{lastName}")
    public ResponseEntity getStudentByFirstNameAndLastName(
            @PathVariable("firstName") final String firstName,
            @PathVariable("lastName") final String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,desc") String[] requestedSort)
            throws JsonProcessingException {
        Pageable pageSorting = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));
        Page<Student> foundStudent = studentRepository.findStudentByFirstNameAndLastName(firstName, lastName, pageSorting);
        if (foundStudent.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(objectMapper.writeValueAsString(foundStudent.getContent()));
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher (@RequestBody final Teacher teacher) {
        List<Teacher> existingTeachers = teacherRepository.findAll();

        if (existingTeachers.contains(teacher))
            return ResponseEntity.unprocessableEntity().build();
        teacherRepository.save(teacher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent (@RequestBody final Student student) {
        List<Student> existingStudents = studentRepository.findAll();

        if (existingStudents.contains(student))
            return ResponseEntity.unprocessableEntity().build();
        studentRepository.save(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher (@PathVariable("id") final Long id, @RequestBody final Teacher teacher){
        Teacher updatedTeacher = teacherRepository.getReferenceById(id);

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
    public ResponseEntity<Student> updateStudent (@PathVariable("id") final Long id, @RequestBody final Student student){
        Student updatedStudent = studentRepository.getReferenceById(id);

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

    @PutMapping("/teacher/{teacherId}/add/student/{studentId}")
    public ResponseEntity addStudentToTeacher (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        Optional<Student> foundStudent = studentRepository.findById(studentId);

        if (foundTeacher.isEmpty() || foundStudent.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (foundTeacher.get().getStudents().contains(foundStudent.get()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        foundTeacher.get().addStudent(foundStudent.get());
        teacherRepository.save(foundTeacher.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/student/{studentId}/add/teacher/{teacherId}")
    public ResponseEntity addTeacherToStudent (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        Optional<Student> foundStudent = studentRepository.findById(studentId);

        if (foundTeacher.isEmpty() || foundStudent.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (foundStudent.get().getTeachers().contains(foundTeacher.get()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        foundStudent.get().addTeacher(foundTeacher.get());
        studentRepository.save(foundStudent.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teacher/{teacherId}/remove/student/{studentId}")
    public ResponseEntity removeStudentFromTeacher (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        Optional<Student> foundStudent = studentRepository.findById(studentId);

        if (foundTeacher.isEmpty() || foundStudent.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (foundTeacher.get().getStudents().contains(foundStudent.get()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        foundTeacher.get().removeStudent(foundStudent.get());
        teacherRepository.save(foundTeacher.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/student/{teacherId}/remove/teacher/{studentId}")
    public ResponseEntity removeTeacherFromStudent (@PathVariable("teacherId") final Long teacherId, @PathVariable("studentId") final Long studentId) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        Optional<Student> foundStudent = studentRepository.findById(studentId);

        if (foundTeacher.isEmpty() || foundStudent.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!foundStudent.get().getTeachers().contains(foundTeacher.get()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        foundStudent.get().removeTeacher(foundTeacher.get());
        studentRepository.save(foundStudent.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher (@PathVariable("id") final Long id){
        teacherRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable("id") final Long id){
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
