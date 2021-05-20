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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809260810214708022L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String mobileNumber;
	
	private Boolean isVerified;
	
	private String profileUrl;
	
	@CreationTimestamp
	private LocalDateTime createdTimeStamp;
	
	@UpdateTimestamp
	private LocalDateTime updateTimeStamp;
	
	@OneToMany(targetEntity = Note.class,fetch = FetchType.EAGER)
	@JoinColumn(name ="user_id")
	private List<Note> notes;
	
	@OneToMany(targetEntity = Label.class)
	@JoinColumn(name = "user_id")
	private List<Label> labels;

	public User(Long id, String firstName, String lastName, String email, String password, String mobileNumber,
			Boolean isVerified, String profileUrl, LocalDateTime createdTimeStamp, LocalDateTime updateTimeStamp,
			List<Note> notes, List<Label> labels) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.isVerified = isVerified;
		this.profileUrl = profileUrl;
		this.createdTimeStamp = createdTimeStamp;
		this.updateTimeStamp = updateTimeStamp;
		this.notes = notes;
		this.labels = labels;
	}
	
	
}
