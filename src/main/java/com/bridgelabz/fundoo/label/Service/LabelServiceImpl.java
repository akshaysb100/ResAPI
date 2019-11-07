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
	public Response addLabel(Long id, LabelsDTO labelDTO) {
		
		System.out.println("data of label : "+labelDTO);
        LabelModel label = modelMapper.map(labelDTO, LabelModel.class); 
        Optional<NoteData> listData = noteRepository.findById(id);
      
        System.out.println(listData);
        label.setId(id);
		label.setCreatedDate(LocalDateTime.now());
		label.setUpdatedDate(LocalDateTime.now());
		labelRepository.save(label);
		
		listData.get().getLabelId().add(label);
		listData.get().setUpdatedDate(LocalDateTime.now());
		
		noteRepository.save(listData.get());
		
		return new Response(LocalDateTime.now(), HttpStatus.OK.value(), enviroment.getProperty("label.created.successfully"));
	
	
      }


	@Override
	public Response deleteLabel(Long labelId) {
		
	//	Optional<LabelModel> deleteLabel = labelRepository.finByLabelId(labelId);
		
		// noteRepository.deleteById(deleteLabel.get().getId());;
		
		//LabelModel label = labelRepository.findByLabelId(labelId);
		
		
		labelRepository.deleteById(labelId);
		//noteRepository.save(addFile)
		
		return null;
	}
}
