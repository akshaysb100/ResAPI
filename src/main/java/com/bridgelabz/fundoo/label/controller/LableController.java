package com.bridgelabz.fundoo.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.label.Service.LabelServiceImpl;
import com.bridgelabz.fundoo.label.dto.Datalab;
import com.bridgelabz.fundoo.label.dto.LabelsDTO;
import com.bridgelabz.fundoo.utility.Response;


@RestController
@RequestMapping(value = "/label")
public class LableController {

	@Autowired
	private LabelServiceImpl labelServiceImpl;
	
	@PostMapping("/addLabel")
	public ResponseEntity<Response> addLabel(@RequestBody LabelsDTO labelsDTO){
		
		Response response = labelServiceImpl.addLabel(labelsDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addLabelWithNote")
	public ResponseEntity<Response> addLabelWithNote(@RequestParam Long id,@RequestBody LabelsDTO labelsDTO ){
		
		Response response = labelServiceImpl.addLabelWithNote(id, labelsDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	@DeleteMapping("/deleteLabel")
	public ResponseEntity<Response> deleteLabel(@RequestParam Long id ){
	
		
		Response response = labelServiceImpl.deleteLabel(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@PutMapping("/addOldLabel")
	public ResponseEntity<Response> addOldLabel(@RequestParam Long labelID,@RequestParam Long noteId){
		
		Response response = labelServiceImpl.addOldLabel(labelID, noteId);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
