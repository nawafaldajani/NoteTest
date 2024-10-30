package com.example.demo;

// src/main/java/com/example/demo1/controller/NoteController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "note_list"; // Thymeleaf view name
    }

    @GetMapping("/new")
    public String createNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "note_form"; // Thymeleaf view name
    }

    @PostMapping
    public String saveNote(@ModelAttribute Note note) {
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String editNoteForm(@PathVariable Long id, Model model) {
        Optional<Note> note = noteService.findById(id);
        note.ifPresent(n -> model.addAttribute("note", n));
        return "note_form"; // Thymeleaf view name
    }

    @GetMapping("/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
        return "redirect:/notes";
    }
}

