package com.adrianwieczorek.contactdirectoryservice.service;
import com.adrianwieczorek.contactdirectoryservice.model.Contact;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    public boolean deleteContact(long id) {
        return contacts.removeIf(c -> c.getId() == id);
    }

    public Optional<Contact> getContactById(long id) {
        return contacts.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact existing = contacts.stream()
                .filter(c -> c.getId() == (id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        existing.setFirstName(updatedContact.getFirstName());
        existing.setLastName(updatedContact.getLastName());
        existing.setEmail(updatedContact.getEmail());
        existing.setPhone(updatedContact.getPhone());

        return existing;
    }
}