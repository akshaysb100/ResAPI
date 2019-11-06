package com.bridgelabz.fundoo.note.controller;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.NoteData;
import com.bridgelabz.fundoo.note.model.service.NoteServiceImpl;
import com.bridgelabz.fundoo.note.payload.UploadFileResponse;
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
	
	@GetMapping(value = "/showData")
	public ResponseEntity<NoteData> showdata(@RequestParam Long id){
		
		NoteData response = noteServiceImpl.showdata(id);
		return new ResponseEntity<NoteData>(response,HttpStatus.OK);
	}
	
	@PostMapping(value = "/uploadFile")
	public UploadFileResponse uploadFile(Long id ,@RequestParam("file") MultipartFile file) {
        NoteData dbFile = noteServiceImpl.storeFile(id, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(Long.toString(dbFile.getId()))
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@GetMapping(value = "/downloadFile/{fileId}")
	 public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
	     
		 NoteData dbFile = noteServiceImpl.getFile(fileId);

	     return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
	                .body(new ByteArrayResource(dbFile.getData()));
	    }
	
	@DeleteMapping("/deleteFile")
	public ResponseEntity<Response> deleteFile(@RequestParam Long id){
		
		
		Response response = noteServiceImpl.deleteFile(id);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
