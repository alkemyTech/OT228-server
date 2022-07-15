package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.MemberDto;

public interface IMemberService {

	MemberDto save(MemberDto memberDto);

	List<MemberDto> findAll();

	boolean delete(Long id);


}