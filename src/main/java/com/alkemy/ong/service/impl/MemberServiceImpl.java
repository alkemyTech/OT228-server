package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.ong.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<MemberDto> findAll(Pageable pageable) {
		return memberRepository.findAll(pageable)
				.map(m -> ModelMapperFacade.map(m, MemberDto.class));
	}

	@Override
	public MemberDto update(MemberDto memberDto, Long id) {
		return memberRepository.findById(id)
				.map(m -> ModelMapperFacade.map(
						memberRepository.save(ModelMapperFacade.map(memberDto, Member.class)),
						MemberDto.class))
				.orElseThrow(() -> new ResourceNotFoundException(messageHandler.membersNotFound));
	}

}
