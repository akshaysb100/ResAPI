package com.bridgelabz.fundoo.note.service;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.LabelModel;
import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.note.model.NoteData;
import com.bridgelabz.fundoo.utility.Response;

public interface NoteService {

	public Response createNote(NoteDTO noteDTO,String token);
	public Response updateNote(Long id,NoteDTO noteDTO,String token);
	public Response deleteNotForever(Long id,String token);
	public Response makeCopy(Long id);
	public Response pinUnpin(Long id);
	public Response deleteNote(Long id);
	public Response restoreData(Long id);
	public Response archive(Long id);
	public NoteData showdata(Long id);
	public NoteData storeFile(Long id,MultipartFile file);
	public Response deleteFile(Long id);
	//public Response addLabel(Long id, LabelDTO labelDto);
	public Response deleLabelforever(Long labelID,Long noteId);
}
