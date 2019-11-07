package com.bridgelabz.fundoo.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/addLable")
	public ResponseEntity<Response> addLabel(@RequestParam Long id,@RequestBody LabelsDTO labelsDTO ){
		System.out.println("add label"+labelsDTO);
		
		Response response = labelServiceImpl.addLabel(id, labelsDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteLabel(@RequestParam Long id ){
	
		
		Response response = labelServiceImpl.deleteLabel(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
}
