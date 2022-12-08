package pl.admonster.UniversityApp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/test")
    public String runTest(){
        return "Hello Vicente!";
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        List<Teacher> existingTeachers = teacherRepository.findAll();

        if(existingTeachers.contains(teacher))
            return ResponseEntity.unprocessableEntity().build();
        teacherRepository.save(teacher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
