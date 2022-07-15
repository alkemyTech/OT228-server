package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alkemy.ong.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.util.MessageHandler;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MessageHandler messageHandler;

	@Override
	public MemberDto save(MemberDto memberDto) {
		if (!memberDto.getName().matches("^[a-zA-Z]*$")) throw new BadRequestException(messageHandler.memberNameRegex);
		return ModelMapperFacade.map(
				memberRepository.save(ModelMapperFacade.map(
						memberDto, Member.class)),
				MemberDto.class);
	}

	@Override
	public List<MemberDto> findAll() {
		return memberRepository.findAll().stream()
				.map(m -> ModelMapperFacade.map(m, MemberDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean delete(Long id) {
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()){
			memberRepository.delete(member.get());
			return true;
		}else{
			throw new NotFoundException(messageHandler.testimoniaNotFound);
		}
	}

}
