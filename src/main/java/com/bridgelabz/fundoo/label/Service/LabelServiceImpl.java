package com.bridgelabz.fundoo.label.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.label.dto.LabelsDTO;
import com.bridgelabz.fundoo.label.model.LabelModel;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.note.model.NoteData;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.utility.Response;


@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment enviroment;
	
	
	@Override
	public Response addLabelWithNote(Long id, LabelsDTO labelDTO) {
		
		
        LabelModel label = modelMapper.map(labelDTO, LabelModel.class); 
        Optional<NoteData> listData = noteRepository.findById(id);
        
        if(listData.isPresent()) {
        //	label.setId(id);
    		label.setCreatedDate(LocalDateTime.now());
    		label.setUpdatedDate(LocalDateTime.now());
    		labelRepository.save(label);
    		
    		listData.get().getLabelId().add(label);
    		listData.get().setUpdatedDate(LocalDateTime.now());
    		
    		noteRepository.save(listData.get());
    		
    		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.created.successfully"),null);
        }
        
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.note.not."),null);

      }


	@Override
	public Response deleteLabel(Long labelId) {
		
		Optional<NoteData> listOfNote = noteRepository.findById(labelId);
		
		LabelModel label = labelRepository.findByLabelId(labelId);
		
		listOfNote.get().getLabelId().remove(label);
		
		noteRepository.save(listOfNote.get());
        
		labelRepository.deleteById(labelId);
		return null;
	}


	@Override
	public Response addOldLabel(Long labelID, Long noteId) {
		
		Optional<NoteData> listOfNote = noteRepository.findById(noteId);
		LabelModel listOfLabel = labelRepository.findByLabelId(labelID);
		
		//listOfLabel.setId(labelID);
		listOfNote.get().getLabelId().add(listOfLabel);
		noteRepository.save(listOfNote.get());
		
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.created.successfully"),null);
	}


	@Override
	public Response addLabel(LabelsDTO labelsDTO) {
		
		LabelModel newLabel = modelMapper.map(labelsDTO, LabelModel.class);
		
		newLabel.setCreatedDate(LocalDateTime.now());
		newLabel.setUpdatedDate(LocalDateTime.now());
		labelRepository.save(newLabel);
		
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.created.successfully"),null);
	}
}
