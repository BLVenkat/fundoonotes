package com.bridgelabz.fundoonotes.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class NoteImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8472022819319075508L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String url;
}
