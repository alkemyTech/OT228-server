package com.alkemy.ong.service;

import com.alkemy.ong.model.Contacts;

import java.util.Optional;

public interface ContactsService {

    Optional<Contacts> getContactById(Long id);
    Contacts save(Contacts contacts);
    void delete(Long id);


}
