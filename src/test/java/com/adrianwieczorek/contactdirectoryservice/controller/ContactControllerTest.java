package com.adrianwieczorek.contactdirectoryservice.controller;
import com.adrianwieczorek.contactdirectoryservice.model.Contact;
import com.adrianwieczorek.contactdirectoryservice.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddValidContact() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("Adrian");
        contact.setLastName("Wieczorek");
        contact.setEmail("adrian@example.com");
        contact.setPhone("123456789");

        Mockito.when(contactService.addContact(any())).thenReturn(contact);

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adrian"));
    }
    @Test
    void testUpdateContact() throws Exception {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName("Adrian");
        contact.setLastName("Wieczorek");
        contact.setEmail("email@example.com");
        contact.setPhone("123456123");

        Mockito.when(contactService.updateContact(eq(1L), any()))
                .thenReturn(contact);

        mockMvc.perform(put("/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Adrian"));
    }

    @Test
    void testAddInvalidContact_MissingEmail() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("Adrian");
        contact.setLastName("Wieczorek");
        contact.setPhone("123456789");

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllContacts() throws Exception {
        Contact c = new Contact();
        c.setFirstName("A");
        c.setLastName("B");
        c.setEmail("a@b.com");
        c.setPhone("123");

        Mockito.when(contactService.getAllContacts()).thenReturn(List.of(c));

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("A"));
    }

    @Test
    void testDeleteContact() throws Exception {
        Mockito.when(contactService.deleteContact(1L)).thenReturn(true);

        mockMvc.perform(delete("/contacts/1"))
                .andExpect(status().isNoContent());
    }
}