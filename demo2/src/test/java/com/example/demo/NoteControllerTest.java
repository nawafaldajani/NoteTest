package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

class NoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void testListNotes() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(view().name("note_list"));
    }

    @Test
    void testCreateNoteForm() throws Exception {
        mockMvc.perform(get("/notes/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("note_form"))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    void testSaveNote() throws Exception {
        mockMvc.perform(post("/notes")
                        .param("title", "New Note")
                        .param("content", "Note Content"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notes"));
    }

    @Test
    void testEditNoteForm() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Existing Note");

        when(noteService.findById(1L)).thenReturn(Optional.of(note));

        mockMvc.perform(get("/notes/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("note_form"))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(get("/notes/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notes"));
    }
}
