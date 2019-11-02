package com.bridgelabz.fundoo.note.model.service;

import com.bridgelabz.fundoo.note.dto.NoteDTO;
import com.bridgelabz.fundoo.utility.Response;

public interface NoteService {

	public Response create(NoteDTO noteDTO);
}
