package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.service.NoteServiceImpl;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
  
	@Autowired
	private NoteServiceImpl noteServiceImpl;
	
	@PutMapping(value = "/create")
	public ResponseEntity<Response> create(NoteDTO noteDTO){
		Response response = noteServiceImpl.create(noteDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}  
	 
}
