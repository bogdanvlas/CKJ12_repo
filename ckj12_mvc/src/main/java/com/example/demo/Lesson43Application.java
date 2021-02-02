package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@SpringBootApplication
public class Lesson43Application {
	public static void main(String[] args) {
		SpringApplication.run(Lesson43Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(NoteRepository noteRepository) {
		CommandLineRunner runner = (args) -> {
			System.out.println("Command line runner init!!!");
			noteRepository.save(new Note("first label", "first text"));
			noteRepository.save(new Note("second note", "second text"));
			noteRepository.save(new Note("my note", "my message"));
			noteRepository.save(new Note("my label", "third msg"));
			System.out.println("4 notes added to repository!");
		};
		return runner;
	}
}
