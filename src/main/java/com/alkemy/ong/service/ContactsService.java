package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contacts;

import java.util.Optional;

public interface ContactsService {

	Optional<Contacts> getContactById(Long id);

	ContactDto save(ContactDto contactDto);

	void delete(Long id);

}
