package com.example.demo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NoteTest {

    @Test
    void testGettersAndSetters() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Title Test");
        note.setContent("Content Test");

        assertEquals(1L, note.getId());
        assertEquals("Title Test", note.getTitle());
        assertEquals("Content Test", note.getContent());
    }

    @Test
    void testNullTitle() {
        Note note = new Note();
        note.setTitle(null);
        assertNull(note.getTitle());
    }

    @Test
    void testNullContent() {
        Note note = new Note();
        note.setContent(null);
        assertNull(note.getContent());
    }
    
    @Test
    void testMaxID() {
    	Note note = new Note();
    	note.setId(Long.MAX_VALUE+1);
    	assertEquals(Long.MIN_VALUE, note.getId());
    	
    }
    @Test
    void testMinID() {
    	Note note = new Note();
    	note.setId(Long.MIN_VALUE-1);
    	assertEquals(Long.MAX_VALUE, note.getId());
    }
    @Test
    void testSpecialCharactersInTitle() {
        Note note = new Note();
        note.setTitle("Special #$%");
        assertEquals("Special #$%", note.getTitle());
    }

    @Test
    void testUpdateNoteWithErrors() {
        Note note = new Note();
        note.setTitle("");
        note.setContent("Some content");

        assertTrue(note.getTitle().isEmpty());
        assertNotNull(note.getContent());
    }
}
