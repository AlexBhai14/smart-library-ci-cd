package com.smartlibrary.bookservice;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookRepository repo;
    public BookController(BookRepository repo){this.repo=repo;}

    @GetMapping public List<Book> all(@RequestParam(required=false) String q){
        if(q==null || q.isBlank()) return repo.findAll();
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q,q);
    }
    @PostMapping public Book add(@RequestBody Book b){return repo.save(b);}
    @PutMapping("/{id}") public Book update(@PathVariable Long id,@RequestBody Book b){
        b.setAvailableQuantity(Math.min(b.getAvailableQuantity(), b.getQuantity()));
        b.setQuantity(b.getQuantity()); return repo.findById(id).map(x -> {
            x.setTitle(b.getTitle()); x.setAuthor(b.getAuthor()); x.setCategory(b.getCategory());
            x.setQuantity(b.getQuantity()); x.setAvailableQuantity(b.getAvailableQuantity()); return repo.save(x);
        }).orElseThrow();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
    @PostMapping("/{id}/reserve") public Book reserve(@PathVariable Long id){
        Book b=repo.findById(id).orElseThrow();
        if(b.getAvailableQuantity()<=0) throw new IllegalStateException("Book unavailable");
        b.setAvailableQuantity(b.getAvailableQuantity()-1); return repo.save(b);
    }
    @PostMapping("/{id}/release") public Book release(@PathVariable Long id){
        Book b=repo.findById(id).orElseThrow();
        b.setAvailableQuantity(Math.min(b.getQuantity(),b.getAvailableQuantity()+1)); return repo.save(b);
    }
}
