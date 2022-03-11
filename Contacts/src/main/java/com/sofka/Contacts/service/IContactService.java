package com.sofka.Contacts.service;

import com.sofka.Contacts.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {

    //    clase para listar los elementos de la base de datos
    public List<Contact> list();

    //    guardar elementos de parametro acepta el usuario a ingresar
    public Contact save(Contact contact);

    //    actualiza objteos de la base de datos recibe como parametro el id del objeto a modificar y el nuevo usuario
    public Contact update(Long id, Contact contact);

    //    borra los elementos que encuentra, se envia un objeto como parametro
    public void delete(Contact contact);

    public Optional<Contact> findContact(Contact contact);

}
