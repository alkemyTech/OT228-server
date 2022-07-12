package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.MemberDto;

public interface IMemberService {

	List<MemberDto> findAll();

}