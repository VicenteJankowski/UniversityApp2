package pl.admonster.UniversityApp2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.admonster.UniversityApp2.model.Student;
import pl.admonster.UniversityApp2.model.Teacher;
import pl.admonster.UniversityApp2.repository.StudentRepository;
import pl.admonster.UniversityApp2.repository.TeacherRepository;

import java.util.*;

@Service
public class UniversityService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ObjectMapper objectMapper;

    private List<Sort.Order> getOrder(String[] requestedSort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (requestedSort[0].contains(",")) {
            for (String sortOrder : requestedSort) {
                String[] splittedRequestedSort = sortOrder.split(",");
                orders.add(new Sort.Order(splittedRequestedSort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, splittedRequestedSort[0]));
            }
        }
        else {
            orders.add(new Sort.Order(requestedSort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, requestedSort[0]));
        }
        return orders;
    }

    public ResponseEntity<Map<String, Object>> getAllStudents(Long teacherId, int page, int size, String[] requestedSort) {
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));
            Page<Student> singlePage = studentRepository.findAll(pagingSort);

            if (teacherId != null) {
                singlePage = studentRepository.findByTeachers_Id(teacherId, pagingSort);
            }

            List<Student> students;
            students = singlePage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("students", students);
            response.put("currentPage", singlePage.getNumber());
            response.put("totalItems", singlePage.getTotalElements());
            response.put("totalPages", singlePage.getTotalPages());

            return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> getAllTeachers(Long studentId, int page, int size, String[] requestedSort)
        throws JsonProcessingException {
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(getOrder(requestedSort)));
            Page<Teacher> singlePage = teacherRepository.findAll(pagingSort);

            if (studentId != null) {
                singlePage = teacherRepository.findByStudents_Id(studentId, pagingSort);
            }

            List<Teacher> teachers;
            teachers = singlePage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("teachers", teachers);
            response.put("currentPage", singlePage.getNumber());
            response.put("totalItems", singlePage.getTotalElements());
            response.put("totalPages", singlePage.getTotalPages());

            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
}
    public ResponseEntity<String> getTeacherByFirstNameAndLastName(String firstName, String lastName)
        throws JsonProcessingException {
            List<Teacher> foundTeacher = teacherRepository.findTeacherByFirstNameAndLastName(firstName, lastName);
            if (foundTeacher.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(objectMapper.writeValueAsString(foundTeacher));
    }

    public ResponseEntity<String> getStudentByFirstNameAndLastName(String firstName, String lastName)
        throws JsonProcessingException {
            List<Student> foundStudent = studentRepository.findStudentByFirstNameAndLastName(firstName, lastName);
            if (foundStudent.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(objectMapper.writeValueAsString(foundStudent));
    }

    public ResponseEntity<Teacher> createTeacher(final Teacher teacher) {
        List<Teacher> existingTeachers = teacherRepository.findAll();

        if (existingTeachers.contains(teacher))
            return ResponseEntity.unprocessableEntity().build();
        teacherRepository.save(teacher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Student> createStudent(final Student student) {
        List<Student> existingStudents = studentRepository.findAll();

        if (existingStudents.contains(student))
            return ResponseEntity.unprocessableEntity().build();
        studentRepository.save(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Teacher> updateTeacher(final Long id, final Teacher teacher) {
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

    public ResponseEntity<Student> updateStudent(final Long id, final Student student) {
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

    public ResponseEntity<HttpStatus> addStudentToTeacher(final Long teacherId, final Long studentId) {
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

    public ResponseEntity<HttpStatus> addTeacherToStudent(final Long teacherId, final Long studentId) {
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

    public ResponseEntity<HttpStatus> removeStudentFromTeacher(final Long teacherId, final Long studentId) {
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

    public ResponseEntity<HttpStatus> removeTeacherFromStudent(final Long teacherId, final Long studentId) {
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

    public ResponseEntity<HttpStatus> deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
