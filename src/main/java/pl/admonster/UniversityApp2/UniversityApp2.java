package pl.admonster.UniversityApp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.admonster.UniversityApp2.model.Student;
import pl.admonster.UniversityApp2.service.UniversityGenerator;

import java.io.FileNotFoundException;

@SpringBootApplication
public class UniversityApp2 {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApp2.class, args);

		try{
			UniversityGenerator.generate(Student.class, 100);
		} catch (FileNotFoundException msg) {
			System.out.println(msg);
		}

	}

}
