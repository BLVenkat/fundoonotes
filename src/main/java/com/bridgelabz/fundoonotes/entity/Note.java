package com.bridgelabz.fundoonotes.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 64425443706094061L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	private String reminder;
	
	private String colour;
	
	private Boolean archive;

	private Boolean pin;
	
	private Boolean trash;
	
	@CreationTimestamp
	private LocalDateTime createdTimeStamp;
	
	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;
	
	@OneToMany(targetEntity = NoteImage.class,fetch = FetchType.EAGER)
	@JoinColumn(name="note_id")
	private List<NoteImage> images;
	
}
