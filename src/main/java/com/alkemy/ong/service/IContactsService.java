package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contacts;

import java.util.Optional;

public interface IContactsService {

	Optional<Contacts> getContactById(Long id);

	ContactDto save(ContactDto contactDto) throws Exception;

	void delete(Long id);

}
