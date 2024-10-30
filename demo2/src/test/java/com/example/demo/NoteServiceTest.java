package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Note note1 = new Note();
        Note note2 = new Note();

        when(noteRepository.findAll()).thenReturn(Arrays.asList(note1, note2));

        List<Note> notes = noteService.findAll();
        assertEquals(2, notes.size());
    }

    @Test
    void testFindById() {
        Note note = new Note();
        note.setId(1L);

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Optional<Note> foundNote = noteService.findById(1L);
        assertTrue(foundNote.isPresent());
    }

    @Test
    void testSave() {
        Note note = new Note();
        note.setTitle("Sample");

        when(noteRepository.save(note)).thenReturn(note);

        Note savedNote = noteService.save(note);
        assertNotNull(savedNote);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        noteService.deleteById(id);
        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteNonExistentNote() {
        Long nonExistentId = 99L;
        doThrow(new IllegalArgumentException("Note not found")).when(noteRepository).deleteById(nonExistentId);

        assertThrows(IllegalArgumentException.class, () -> noteService.deleteById(nonExistentId));
    }

    @Test
    void testSaveNoteWithoutTitle() {
        Note note = new Note();
        note.setContent("Content without title");

        when(noteRepository.save(note)).thenReturn(note);

        Note savedNote = noteService.save(note);
        assertNull(savedNote.getTitle());
    }

    @Test
    void testSaveNoteWithoutContent() {
        Note note = new Note();
        note.setTitle("Title without content");

        when(noteRepository.save(note)).thenReturn(note);

        Note savedNote = noteService.save(note);
        assertNull(savedNote.getContent());
    }
    @Test
    void testSaveNoteWithSpecialCharacters() {
        Note note = new Note();
        note.setTitle("Special #$%@! Title");
        note.setContent("Content with special characters #$%@!");

        when(noteRepository.save(note)).thenReturn(note);

        Note savedNote = noteService.save(note);
        assertEquals("Special #$%@! Title", savedNote.getTitle(), "Title with special characters should save correctly");
        assertEquals("Content with special characters #$%@!", savedNote.getContent());
    }
}
