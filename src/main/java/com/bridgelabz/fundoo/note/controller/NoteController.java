package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.service.NoteServiceImpl;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
  
	@Autowired
	private NoteServiceImpl noteServiceImpl;
	
	@PostMapping(value = "/createNote")
	public ResponseEntity<Response> create(@RequestBody NoteDTO noteDTO){
		
		Response response = noteServiceImpl.createNote(noteDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}  
	
	@PutMapping(value = "/updateNote/{id}")
	public ResponseEntity<Response> updateNote(@PathVariable Long id,@RequestBody NoteDTO noteDTO){
		
		Response response = noteServiceImpl.updateNote(id,noteDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNoteForever")
	public ResponseEntity<Response> deleteNoteForever(@RequestParam Long id){
	    
		Response response = noteServiceImpl.deleteNotForever(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/deletenote")
	public ResponseEntity<Response> deleteNote(@RequestParam Long id){
		
		Response response = noteServiceImpl.deleteNote(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK); 
		
	}
	
	@PutMapping("/restoreData")
	public ResponseEntity<Response> restoreData(@RequestParam Long id){
		
		Response response = noteServiceImpl.restoreData(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/makeCopy")
	public ResponseEntity<Response> makeCopy(@RequestParam Long id){
		
		Response response = noteServiceImpl.makeCopy(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping(value = "/pinUnpinData")
	public ResponseEntity<Response> pinUnpinData(@RequestParam Long id){
		
		Response response = noteServiceImpl.pinUnpin(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping(value = "/archive")
	public ResponseEntity<Response> archive(@RequestParam Long id){
		
		Response response = noteServiceImpl.archive(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
	
}
