package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@Controller
@RequestMapping("/notes")
public class NoteController {

	private NoteRepository noteRepository;

	@Autowired
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@GetMapping("/all")
	public String notes(Model model) {
		model.addAttribute("notes", noteRepository.findAll());
		return "notes";
	}

	@GetMapping("/create")
	public String create() {
		return "note_form";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("note") Note note) {
		noteRepository.save(note);
		return "redirect:/notes/all";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		noteRepository.deleteById(id);
		return "redirect:/notes/all";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name = "word") String word, Model model) {
		// поиск записок по label
		List<Note> notes = noteRepository.findByLabelContainingOrMessageContaining(word, word);
		model.addAttribute("notes", notes);
		return "notes";
	}
}
