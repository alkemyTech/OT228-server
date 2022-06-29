package com.alkemy.ong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Contacts;
import com.alkemy.ong.repository.ContactsRepository;
import com.alkemy.ong.service.ContactsService;

@Service
public class ContactServiceImpl implements ContactsService {

	@Autowired
	private ContactsRepository contactRepository;
	
	@Override
	public Optional<Contacts> getContactById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactDto save(ContactDto contactDto) {
		return ModelMapperFacade.map(
				contactRepository.save(ModelMapperFacade.map(
						contactDto, Contacts.class)),
				ContactDto.class);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

}
