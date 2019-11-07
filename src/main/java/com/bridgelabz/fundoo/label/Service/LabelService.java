package com.bridgelabz.fundoo.label.Service;

import com.bridgelabz.fundoo.label.dto.LabelsDTO;
import com.bridgelabz.fundoo.utility.Response;

public interface LabelService {

	public Response addLabel(Long id,LabelsDTO labelDTO);
	public Response deleteLabel(Long labelId);
}
