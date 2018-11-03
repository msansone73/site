package br.com.msansone.api.site.controller;


import br.com.msansone.api.site.exception.ResourceNotFoundException;
import br.com.msansone.api.site.model.usuario;
import br.com.msansone.api.site.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Get All usuarios
    @GetMapping("/usuarios")
    public List<usuario> getAllusuarios() {
        return usuarioRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/usuarios")
    public usuario createNote(@Valid @RequestBody usuario note) {
        return usuarioRepository.save(note);
    }

    // Get a Single Note
    @GetMapping("/usuarios/{id}")
    public usuario getNoteById(@PathVariable(value = "id") Long noteId) {
        return usuarioRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/usuarios/{id}")
    public usuario updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody usuario noteDetails) {

        usuario note = usuarioRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setNome(noteDetails.getNome());
        note.setEmail(noteDetails.getEmail());
        note.setSenha(noteDetails.getSenha());

        usuario updatedNote = usuarioRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        usuario note = usuarioRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        usuarioRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}