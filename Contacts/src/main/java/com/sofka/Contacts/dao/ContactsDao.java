package com.sofka.Contacts.dao;


import com.sofka.Contacts.domain.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

// interfaz para hacer peticiones especiales en la base de datos
public interface ContactsDao extends CrudRepository<Contact, Long> {
    @Modifying
    @Query("update Contact cont set cont.name = :name, cont.dateUpdate = :update where cont.id = :id")
    public void updateNombre(
            @Param(value = "id") Long id,
            @Param(value = "name") String name,
            @Param(value = "update") Date update);

    @Modifying
    @Query("update Contact cont set cont.phone = :phone, cont.dateUpdate = :update where cont.id = :id")
    public void updateTelefono(
            @Param(value = "id") Long id,
            @Param(value = "phone") String phone,
            @Param(value = "update") Date update);

    @Modifying
    @Query("update Contact cont set cont.email = :email, cont.dateUpdate = :update where cont.id = :id")
    public void updateCorreo(
            @Param(value = "id") Long id,
            @Param(value = "email") String email,
            @Param(value = "update") Date update);

    @Modifying
    @Query("update Contact cont set cont.dateBirth = :dateBirth, cont.dateUpdate = :update where cont.id = :id")
    public void updateDiaNacimiento(
            @Param(value = "id") Long id,
            @Param(value = "dateBirth") Date dateBirth,
            @Param(value = "update") Date update);

    @Modifying
    @Query("update Contact cont set cont.dateDelete = :dateDelete, cont.dateUpdate = :update where cont.id = :id")
    public void updateEliminar(
            @Param(value = "id") Long id,
            @Param(value = "dateDelete") Date dateDelete,
            @Param(value = "update") Date update);
}
