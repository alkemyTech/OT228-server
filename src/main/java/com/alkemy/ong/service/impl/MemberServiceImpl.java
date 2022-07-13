package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<MemberDto> findAll() {
		return memberRepository.findAll().stream()
				.map(m -> ModelMapperFacade.map(m, MemberDto.class))
				.collect(Collectors.toList());
	}

}
