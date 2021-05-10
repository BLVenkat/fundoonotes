package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;

public interface LabelService {

	public Label createLabel(String token,LabelDto labelDto);
	
	public List<Label> getUserLabels(String token);
	
	public void deleteLabel(String token,Long labelId);
	
	public Label updateLabel(String token,Long labelId,LabelDto labelDto);
}
