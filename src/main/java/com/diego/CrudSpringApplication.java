package com.diego;

import com.diego.enums.Category;
import com.diego.model.Course;
import com.diego.model.Lesson;
import com.diego.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setNome("Angular com Spring");
			c.setCategory(Category.BACk_END);


			Lesson l = new Lesson();
			l.setNome("Introdução");
			l.setYoutubeUrl("watch?=v1");
			l.setCourse(c);
			c.getLessons().add(l);


			Lesson l1 = new Lesson();
			l1.setNome("Angular");
			l1.setYoutubeUrl("watch?=v2");
			l1.setCourse(c);
			c.getLessons().add(l1);

			courseRepository.save(c);

	};

}
}

