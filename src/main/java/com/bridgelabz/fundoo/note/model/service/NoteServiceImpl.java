package com.bridgelabz.fundoo.note.model.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.bridgelabz.fundoo.exception.FileStorageException;
import com.bridgelabz.fundoo.exception.MyFileNotFoundException;
import com.bridgelabz.fundoo.exception.NoteNotCreate;
import com.bridgelabz.fundoo.exception.NoteNoteUpdate;
import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.NoteData;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.utility.Response;

@PropertySource(name = "note",value = {"classpath:noteresponse.properties"})
@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment enviroment;
	
	@Override
	public Response createNote(NoteDTO noteDTO) throws NoteNotCreate {
		
		String titel = noteDTO.getTitle();
		String description = noteDTO.getDescription();
		
		if((titel!=null && !titel.isEmpty()) || (description!=null && !description.isEmpty())) {
		
			NoteData note = modelMapper.map(noteDTO, NoteData.class);
			note.setCreatedDate(LocalDateTime.now());
			note.setUpdatedDate(LocalDateTime.now());
			noteRepository.save(note);
			return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.created"));
		}else {
			throw new NoteNotCreate(enviroment.getProperty("note.not.created"));
		}
		
	}
	
	@Override
	public Response updateNote(Long id,NoteDTO noteDTO) {
  		
		Optional<NoteData> updateNote = noteRepository.findById(id);
		if(updateNote !=null ) {
			
			updateNote.get().setTitle(noteDTO.getTitle());
			updateNote.get().setDescription(noteDTO.getDescription());
			updateNote.get().setUpdatedDate(LocalDateTime.now());
			noteRepository.save(updateNote.get());
			
			return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.update.sucessfully"));
		}else {
			throw new NoteNotCreate(enviroment.getProperty("note.not.updated"));
		}

	}

	@Override
	public Response deleteNotForever(Long id) {
		
		Optional<NoteData> deleteNote= noteRepository.findById(id);
		if(deleteNote!=null) {

			if(id == deleteNote.get().getId()) {
		
				noteRepository.deleteById(id);
				return new Response(LocalDateTime.now(),HttpStatus.OK.value(),enviroment.getProperty("note.delete.success"));
			}else {
				throw new NoteNoteUpdate(enviroment.getProperty("note.not.delete"));
			}
		}else {
			throw new NoteNoteUpdate(enviroment.getProperty("note.not.delete"));
		}
		
	}

	@Override
	public Response makeCopy(Long id) {
		
		Optional<NoteData> notedata = noteRepository.findById(id);
		
		NoteData noteData = modelMapper.map(notedata,NoteData.class);
		
		noteData.setTitle(notedata.get().getTitle());
		noteData.setDescription(notedata.get().getDescription());
		noteData.setCreatedDate(LocalDateTime.now());
		noteData.setUpdatedDate(LocalDateTime.now());
		noteRepository.save(noteData);
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.copy.same"));
	}

	@Override
	public Response pinUnpin(Long id) {
		
		Optional<NoteData> pinData = noteRepository.findById(id);
	   
        boolean value = pinData.get().isPinUnpinData();
      
        if(value == false) {
        	pinData.get().setPinUnpinData(true);
        	noteRepository.save(pinData.get());
        }else {
        	pinData.get().setPinUnpinData(false);
        	noteRepository.save(pinData.get());
        }
	
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.pin.update"));
	}

	@Override
	public Response deleteNote(Long id) {
		
		Optional<NoteData> deleteData = noteRepository.findById(id);
		   
		deleteData.get().setTrash(true);
        noteRepository.save(deleteData.get());
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.trashed"));
	
	}

	@Override
	public Response restoreData(Long id) {
		
		Optional<NoteData> deleteData = noteRepository.findById(id);
		   
		deleteData.get().setTrash(false);
        noteRepository.save(deleteData.get());
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.restore"));
		
	}

	@Override
	public Response archive(Long id) {
		
		Optional<NoteData> archiveData = noteRepository.findById(id);
		   
        boolean value = archiveData.get().isArchive();
      
        if(value == false) {
        	archiveData.get().setArchive(true);
        	noteRepository.save(archiveData.get());
    		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.archive"));
        }else {
        	archiveData.get().setArchive(false);
        	noteRepository.save(archiveData.get());
    		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.unArchive"));

        }
	
	}

	@Override
	public NoteData showdata(Long id) {

		Optional<NoteData> showDAta = noteRepository.findById(id);
		
		if(showDAta.isPresent()) {
			
			return showDAta.get();
		}
		return null;
	}

	@Override
	public NoteData storeFile(Long id,MultipartFile file)  {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Optional<NoteData> addFile = noteRepository.findById(id);
	//	NoteData noteData = modelMapper.map(addFile,NoteData.class);
       
        try {

        	if(fileName.contains("..")) {
             	throw new FileStorageException("note.not.upload.file");
             }
        	
		//	noteData = new NoteData(fileName, file.getContentType(), file.getBytes());
	        addFile.get().setFileName(fileName);
	        addFile.get().setData(file.getBytes());
	        addFile.get().setFileType(file.getContentType());
			return noteRepository.save(addFile.get());
			
        } catch (IOException e) {
			
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
            
        } 

	}
	
	public NoteData getFile(Long fileId) {
        return noteRepository.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

	@Override
	public Response deleteFile(Long id) {
		
		Optional<NoteData> deleteFile = noteRepository.findById(id);

		if(deleteFile.isPresent()) {
			
			deleteFile.get().setFileName(null);
			deleteFile.get().setFileType(null);
			deleteFile.get().setData(null);
		    noteRepository.save(deleteFile.get());
			return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.file.delete.sucessfully"));

		}else {
            throw new FileStorageException("note.id.wrong");

		}
	
	}
    
}
