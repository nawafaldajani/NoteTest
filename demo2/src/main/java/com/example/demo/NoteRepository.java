package com.example.demo;

// src/main/java/com/example/demo1/repository/NoteRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}

