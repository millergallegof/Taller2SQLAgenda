package com.sofka.Contacts.controller;

import com.sofka.Contacts.domain.Contact;
import com.sofka.Contacts.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000", methods =
        {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/")
    public Map<String, String> index() {

        var respuesta = new HashMap<String, String>();
        respuesta.put("message", "Hola Mundo");
        return respuesta;
    }

    @GetMapping(path = "/contacts")
    public List<Contact> list() {
        try {
            log.info("Proceso Exitoso");
            return contactService.list();
        } catch (Exception exc) {
            log.info("ERROR" + exc.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/contact")
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        try {
            log.info("Contacto Creado " + contact);
            contactService.save(contact);

            return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
        } catch (Exception exc) {
            log.info("ERROR create " + exc.getMessage());
            return new ResponseEntity<Contact>(contact, HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> delete(Contact contact) {
        try {
            log.info("Contacto a eliminar: {}", contact);
            contactService.delete(contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

//
//    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    @PutMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") Long id, @RequestBody Contact contact) {

        try {
            log.info("Contacto a modificar: {}", contact);
            contactService.update(id, contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

    @PatchMapping(path = "/contact/name/{id}")
    public ResponseEntity<Contact> updateName(Contact contact, @PathVariable("id") Long id) {
        try {
            contactService.updateNombre(id, contact);
            log.info("Contacto No. {}", contact.getId());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

    @PatchMapping(path = "/contact/phone/{id}")
    public ResponseEntity<Contact> updatephone(Contact contact, @PathVariable("id") Long id) {
        try {
            contactService.updateTelefono(id, contact);
            log.info("Contacto No. {}", contact.getId());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/contact/email/{id}")
    public ResponseEntity<Contact> updateEmail(Contact contact, @PathVariable("id") Long id) {
        try {
            contactService.updateCorreo(id, contact);
            log.info("Contacto No. {}", contact.getEmail());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

    @PatchMapping(path = "/contact/dateBirth/{id}")
    public ResponseEntity<Contact> updateBirth(Contact contact, @PathVariable("id") Long id) {
        try {
            contactService.updateDiaNacimiento(id, contact);
            log.info("Contacto No. {}", contact.getDateBirth());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

    @PatchMapping(path = "/contact/dateDelete/{id}")
    public ResponseEntity<Contact> updateDelete(@PathVariable("id") Long id, @RequestBody Contact contact) {
        try {
            contactService.updateEliminar(id, contact);
            log.info("Contacto No. {}", contact.getDateDelete());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception exc) {
            log.info("ERROR " + exc.getMessage());
            return new ResponseEntity<>(contact, HttpStatus.CONFLICT);
        }

    }

}
