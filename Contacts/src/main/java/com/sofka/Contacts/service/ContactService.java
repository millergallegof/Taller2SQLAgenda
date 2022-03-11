package com.sofka.Contacts.service;

import com.sofka.Contacts.dao.ContactsDao;
import com.sofka.Contacts.domain.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService implements IContactService {

    @Autowired //para inyectar ContactsDao
    private ContactsDao contactsDao;

//lista los elementos de la base de datos
    @Override
    @Transactional(readOnly = true)
    public List<Contact> list() {
        try {
            return ((List<Contact>) contactsDao.findAll());
        } catch (Exception exc) {
            log.info(exc.getMessage());
            return null;
        }

    }

//guarda el elemento que se le envia como parametro en la base de datos
    @Override
    @Transactional
    public Contact save(Contact contact) {
        return contactsDao.save(contact);
    }

// actualiza todos los campos de el elemento que se le envia el id segun los datos del usuario que se le envia
    @Override
    @Transactional
    public Contact update(Long id, Contact contact) {
        contact.setId(id);
        return contactsDao.save(contact);
    }

    //Modificar nombre unicamente
    @Transactional
    public void updateNombre(Long id, Contact contact) {
        long miliseconds = System.currentTimeMillis();
        Date date1 = new Date(miliseconds);
        contactsDao.updateNombre(id, contact.getName(), date1 );
    }

    //Modificar telefono unicamente
    @Transactional
    public void updateTelefono(Long id, Contact contact) {
        long miliseconds = System.currentTimeMillis();
        Date date1 = new Date(miliseconds);
        contactsDao.updateTelefono(id, contact.getPhone(), date1);
    }

    //Modificar correo unicamente
    @Transactional
    public void updateCorreo(Long id, Contact contact) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        contactsDao.updateCorreo(id, contact.getEmail(), date);
    }

    //Modificar dia nacimiento unicamente
    @Transactional
    public void updateDiaNacimiento(Long id, Contact contact) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        contactsDao.updateDiaNacimiento(id, contact.getDateBirth(), date);
    }

    //Modificar dia borrado unicamente
    @Transactional
    public void updateEliminar(Long id, Contact contact) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        contactsDao.updateEliminar(id, contact.getDateDelete(), date);
    }

// Se hace un borrado fisico del elemento que se envia como parametro
    @Override
    @Transactional
    public void delete(Contact contact) {
        contactsDao.delete(contact);
    }

//se busca un elemento segun el usuario que se envia como parametro
    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> findContact(Contact contact) {
        return contactsDao.findById(contact.getId());
    }
}
