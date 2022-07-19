package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMemberService {

	MemberDto save(MemberDto memberDto);

	Page<MemberDto> findAll(Pageable pageable);

	MemberDto update(MemberDto memberDto, Long id);



}