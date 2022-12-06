package pl.admonster.UniversityApp2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityAppController {

    @GetMapping("/test")
    public String runTest(){
        return "Hello Vicente!";
    }



}
