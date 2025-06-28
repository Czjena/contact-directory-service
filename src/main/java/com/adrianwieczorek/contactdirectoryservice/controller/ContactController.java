package com.adrianwieczorek.contactdirectoryservice.controller;
import com.adrianwieczorek.contactdirectoryservice.model.Contact;
import com.adrianwieczorek.contactdirectoryservice.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody Contact contact) {
        Contact updated = contactService.updateContact(id, contact);
        return ResponseEntity.ok(updated);
    }
    // Pobierz wszystkie kontakty
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // Dodaj nowy kontakt
    @PostMapping
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
        Contact saved = contactService.addContact(contact);
        return ResponseEntity.ok(saved);
    }

    // Usuń kontakt po id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable long id) {
        boolean removed = contactService.deleteContact(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}