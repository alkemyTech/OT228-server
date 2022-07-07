package com.alkemy.ong.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contacts;

public interface IContactsService {

	Optional<Contacts> getContactById(Long id);

	ContactDto save(ContactDto contactDto) throws Exception;

	void delete(Long id);
	
	List<ContactDto> findAll();

}
