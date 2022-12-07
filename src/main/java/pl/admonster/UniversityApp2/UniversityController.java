package pl.admonster.UniversityApp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/test")
    public String runTest(){
        return "Hello Vicente!";
    }


}
