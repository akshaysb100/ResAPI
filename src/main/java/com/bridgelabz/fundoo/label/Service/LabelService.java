package com.bridgelabz.fundoo.label.Service;

import com.bridgelabz.fundoo.label.dto.LabelsDTO;
import com.bridgelabz.fundoo.utility.Response;

public interface LabelService {

	public Response addLabel(LabelsDTO labelsDTO);
	public Response addLabelWithNote(Long id,LabelsDTO labelDTO);
	public Response addOldLabel(Long labelID,Long noteId);
	public Response deleteLabel(Long labelId);
}
