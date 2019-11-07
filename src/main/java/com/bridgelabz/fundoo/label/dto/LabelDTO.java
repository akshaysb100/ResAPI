package com.bridgelabz.fundoo.label.dto;

public class LabelDTO {

private String labelName;

public String getLabelName() {
	return labelName;
}

public void setLabelName(String labelName) {
	this.labelName = labelName;
}

@Override
public String toString() {
	return "LabelDTO [labelName=" + labelName + "]";
}


	
}
