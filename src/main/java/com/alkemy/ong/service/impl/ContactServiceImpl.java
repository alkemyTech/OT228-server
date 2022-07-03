package com.alkemy.ong.service.impl;

import java.util.Optional;

import com.alkemy.ong.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Contacts;
import com.alkemy.ong.repository.ContactsRepository;
import com.alkemy.ong.service.IContactsService;

@Service
public class ContactServiceImpl implements IContactsService {

	@Autowired
	private ContactsRepository contactRepository;

	@Autowired
	private IEmailService emailService;
	
	@Override
	public Optional<Contacts> getContactById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactDto save(ContactDto contactDto) throws Exception{

		emailService.sendContactConfirmation(contactDto.getEmail(), contactDto.getName());
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
