package com.bridgelabz.fundoo.note.model;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class NoteData {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;

	@Column(name = "CreatedDate")
	private LocalDateTime CreatedDate;
	
	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;
	
	@Column(name = "pinUnpinData", nullable = false)
	private boolean pinUnpinData;
	
	@Column(name = "trash",nullable = false)
	private boolean trash;
	
	@Column(name = "archive",nullable = false)
	private boolean archive;
	
	private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

	public NoteData() {
		
	}

	public NoteData(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}



	public NoteData(long id, String title, String description, LocalDateTime createdDate, LocalDateTime updatedDate,
			boolean pinUnpinData, boolean trash, boolean archive) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		CreatedDate = createdDate;
		this.updatedDate = updatedDate;
		this.pinUnpinData = pinUnpinData;
		this.trash = trash;
		this.archive = archive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isPinUnpinData() {
		return pinUnpinData;
	}

	public void setPinUnpinData(boolean pinUnpinData) {
		this.pinUnpinData = pinUnpinData;
	}
 	
	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}
    
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NoteData [id=" + id + ", title=" + title + ", description=" + description + ", CreatedDate="
				+ CreatedDate + ", updatedDate=" + updatedDate + ", pinUnpinData=" + pinUnpinData + ", trash=" + trash
				+ ", archive=" + archive + ", fileName=" + fileName + ", fileType=" + fileType + ", data="
				+ Arrays.toString(data) + "]";
	}

}
