package com.example.demo;

import com.datastax.driver.core.utils.UUIDs;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotesController {

  private final NotesRepository notesRepository;

  @GetMapping("/notes")
  public ResponseEntity<List<Note>> getAll() {
    return new ResponseEntity<>(notesRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/notes/{id}")
  public ResponseEntity<Note> get(@PathVariable("id") UUID uuid) {
    return notesRepository.findById(uuid)
        .map(note -> new ResponseEntity<>(note, HttpStatus.OK))
        .orElseGet(() -> ResponseEntity.notFound().build());

  }

  @PostMapping("/notes")
  public ResponseEntity<Note> create(@RequestBody NoteDTO noteDto) {
    Note note = notesRepository.save(new Note(UUIDs.timeBased(), noteDto.getContent()));
    return new ResponseEntity<>(note, HttpStatus.CREATED);
  }

  @DeleteMapping("/notes/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
    notesRepository.deleteById(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
