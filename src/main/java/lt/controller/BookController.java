package lt.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lt.entities.Book;
import lt.repository.BookRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BookController {

  private final Logger log = LoggerFactory.getLogger(BookController.class);
  private BookRepository repo;

  @Value("${app.mensaje}")
  protected String message;

  public BookController(BookRepository repo) {
    this.repo = repo;
  }

  /**
   * Buscar todos los libros
   */
  @GetMapping("/api/books")
  public List<Book> findAll() {
    System.out.println(message);
    return repo.findAll();
  }

  /**
   * Buscar un libro por su id
   */
  @GetMapping("/api/books/{id}")
  @Operation(summary = "Buscar un libro por su id", description = "Devuelve un libro por su id")
  public ResponseEntity<Book> findOneById(@PathVariable Long id) {

    Optional<Book> book = repo.findById(id);
    if (book.isPresent()) {
      return ResponseEntity.ok(book.get());
    } else {
      return ResponseEntity.notFound().build();
    }

    // Programacion funcional
    // return repo.findById(id).map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
  }

  /**
   * Crear un libro
   */
  @PostMapping("/api/books")
  public ResponseEntity<Book> create(@RequestBody Book book) {
    if (book.getId() != null) {
      log.warn("Trying to create a new book with id");
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(repo.save(book));
  }

  /**
   * Actualizar un libro
   */
  @PutMapping("/api/books")
  public ResponseEntity<Book> putMethodName(@RequestBody Book book) {

    if (book.getId() == null) {
      log.warn("Trying to update a book without id");
      return ResponseEntity.badRequest().build();
    }
    if (!repo.existsById(book.getId())) {
      log.warn("Trying to update a book that does not exist");
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(repo.save(book));
  }

  /**
   * Eliminar un libro
   */
  @DeleteMapping("/api/books/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) {
      log.warn("Trying to delete a book that does not exist");
      return ResponseEntity.notFound().build();
    }
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Eliminar todos los libros
   */
  @DeleteMapping("/api/books")
  public ResponseEntity<Void> deleteAll() {
    repo.deleteAll();
    return ResponseEntity.noContent().build();
  }
}
