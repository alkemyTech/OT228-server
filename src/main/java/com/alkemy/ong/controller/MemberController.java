package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.hateoas.IHateoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.service.IMemberService;

import java.util.List;

import com.alkemy.ong.util.MessageHandler;

@RestController
@RequestMapping(MemberController.MEMBERS)
public class MemberController {

	protected static final String MEMBERS = "/members";
	protected static final String ID = "/{id}";
	private static final Integer SIZE = 10;


	@Autowired
	private IMemberService memberService;

	@Autowired
	private MessageHandler messageHandler;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody MemberDto memberDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(memberDto));
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(name = "page", required = true) String page) {
		Pageable pageable = PageRequest.of(Integer.parseInt(page), SIZE);
		Page<MemberDto> result = memberService.findAll(pageable);
		return ResponseEntity.ok().body(!result.isEmpty() ?
				IHateoas.addPaginationLinks(result) : messageHandler.membersNotFound);
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateMember (@Valid @PathVariable(name = "id") long id,
										   @RequestBody MemberDto memberDto){

		MemberDto memberResponce = memberService.update(memberDto,id);
		return new ResponseEntity<>(memberResponce, HttpStatus.OK);
	}
	
	@DeleteMapping(MemberController.ID)
	public ResponseEntity<?> deleteMember(@Valid @RequestParam Long id){
		if(memberService.delete(id)){
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}