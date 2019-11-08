package com.bridgelabz.fundoo.note.service;

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
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.LabelModel;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.NoteData;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.user.model.UserData;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.utility.TokenUtil;

@PropertySource(name = "note",value = {"classpath:noteresponse.properties"})
@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Response createNote(NoteDTO noteDTO,String token) throws NoteNotCreate {
		
		Long userid = tokenUtil.decodeToken(token);
		
		Optional<UserData> emailList = userRepository.findById(userid);
		
		String titel = noteDTO.getTitle();
		String description = noteDTO.getDescription();
		
		if(emailList.isPresent()) {
			
			if((titel!=null && !titel.isEmpty()) || (description!=null && !description.isEmpty())) {
				
				NoteData note = modelMapper.map(noteDTO, NoteData.class);
				note.setCreatedDate(LocalDateTime.now());
				note.setUpdatedDate(LocalDateTime.now());
				noteRepository.save(note);
				
				emailList.get().getNoteID().add(note);
				userRepository.save(emailList.get());
				
				return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.created"),null);
			}else {
				throw new NoteNotCreate(enviroment.getProperty("note.not.created"));
			}
			
		}else {
			throw new NoteNotCreate(enviroment.getProperty("status.create.usernotexit"));
	 }
		
		
	}
	
	@Override
	public Response updateNote(Long id,NoteDTO noteDTO,String token) {
  		
		Long userId = tokenUtil.decodeToken(token);
		
		Optional<UserData> userIdList = userRepository.findById(userId); 
		
		Optional<NoteData> updateNote = noteRepository.findById(id);
		
		if(userIdList.isPresent()) {
			
			if(updateNote !=null ) {
				
				updateNote.get().setTitle(noteDTO.getTitle());
				updateNote.get().setDescription(noteDTO.getDescription());
				updateNote.get().setUpdatedDate(LocalDateTime.now());
				noteRepository.save(updateNote.get());
				
//				userIdList.get().getNoteID().set(Integer.valueOf(id.intValue()), updateNote.get());
//				userRepository.save(userIdList.get());
				
				return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.update.sucessfully"),null);
			}else {
				throw new NoteNotCreate(enviroment.getProperty("note.not.updated"));
			}
		}else {
			throw new NoteNotCreate(enviroment.getProperty("status.create.usernotexit"));
		}
	

	}

	@Override
	public Response deleteNotForever(Long id,String token) {
		
		Long userId = tokenUtil.decodeToken(token);
		
		Optional<UserData> userIdList = userRepository.findById(userId);
		
		Optional<NoteData> deleteNote= noteRepository.findById(id);
		if(deleteNote!=null) {

			if(id == deleteNote.get().getId()) {
		
				userIdList.get().getNoteID().remove(deleteNote.get());
				userRepository.save(userIdList.get());
				noteRepository.deleteById(id);
				
				return new Response(LocalDateTime.now(),HttpStatus.OK.value(),enviroment.getProperty("note.delete.success"),null);
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
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.copy.same"),null);
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
	
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.pin.update"),null);
	}

	@Override
	public Response deleteNote(Long id) {
		
		Optional<NoteData> deleteData = noteRepository.findById(id);
		   
		deleteData.get().setTrash(true);
        noteRepository.save(deleteData.get());
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.trashed"),null);
	
	}

	@Override
	public Response restoreData(Long id) {
		
		Optional<NoteData> deleteData = noteRepository.findById(id);
		   
		deleteData.get().setTrash(false);
        noteRepository.save(deleteData.get());
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.restore"),null);
		
	}

	@Override
	public Response archive(Long id) {
		
		Optional<NoteData> archiveData = noteRepository.findById(id);
		   
        boolean value = archiveData.get().isArchive();
      
        if(value == false) {
        	archiveData.get().setArchive(true);
        	noteRepository.save(archiveData.get());
    		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.archive"),null);
        }else {
        	archiveData.get().setArchive(false);
        	noteRepository.save(archiveData.get());
    		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.unArchive"),null);

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
			return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("note.file.delete.sucessfully"),null);

		}else {
            throw new FileStorageException("note.id.wrong");

		}
	
	}

	@Override
	public Response deleLabelforever(Long labelID, Long noteId) {
        
		Optional<NoteData> noteList = noteRepository.findById(noteId);
		
		LabelModel labelList = labelRepository.findByLabelId(labelID);
		
		noteList.get().getLabelId().remove(noteId);
		labelRepository.delete(labelList);
		noteRepository.save(noteList);   
		return null;
	}

//	@Override
//	public Response addLabel(Long id, LabelDTO labelDto)  {
//		
//		LabelData label = modelMapper.map(labelDto, LabelData.class); 
//		
//		label.setCreatedDate(LocalDateTime.now());
//		label.setUpdatedDate(LocalDateTime.now());
//		
//		Optional<NoteData> listData = noteRepository.findById(id);
//		
//		listData.get().getLabelDatas().add(label);
//		listData.get().setUpdatedDate(LocalDateTime.now());
//
//		noteRepository.save(listData.get());
//		
//		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.created.sucessfully"));
//	}
    
}
