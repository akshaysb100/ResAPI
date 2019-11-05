package com.bridgelabz.fundoo.note.model.service;

import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.utility.Response;

public interface NoteService {

	public Response createNote(NoteDTO noteDTO);
	public Response updateNote(Long id,NoteDTO noteDTO);
	public Response deleteNotForever(Long id);
	public Response makeCopy(Long id);
	public Response pinUnpin(Long id);
	public Response deleteNote(Long id);
	public Response restoreData(Long id);
	public Response archive(Long id);
	
}
