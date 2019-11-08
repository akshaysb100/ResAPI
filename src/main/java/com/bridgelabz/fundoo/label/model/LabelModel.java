package com.bridgelabz.fundoo.label.model;

import java.time.LocalDateTime;

import javax.persistence.Column;                                                                   
import javax.persistence.Entity;                                                                   
import javax.persistence.GeneratedValue;                                                           
import javax.persistence.GenerationType;                                                           
import javax.persistence.Id;
import javax.persistence.Table;                                                                    
                                                                                                                                                    
                                                                                                                                                                                                                                                                                                                                                                                                  
@Entity                                                                                            
@Table(name = "label")                                                                             
public class LabelModel {                                                                           
                                                                                                   
	@Id                                                                                            
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                                
	@Column(name = "labelId")                                                                           
	private Long labelId;                                                                               
	                                                                                               
	@Column(name = "labelName")                                                                        
	private String labelName;                                                                          
	                                                                                               
	@Column(name = "createdDate")                                                                  
	private LocalDateTime createdDate;                                                             
	                                                                                               
	@Column(name = "updatedDate")                                                                  
	private LocalDateTime updatedDate;                                                             
   
	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "LabelModel [labelId=" + labelId + ", labelName=" + labelName + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}

}                                                                                                  
                                                                                                   
