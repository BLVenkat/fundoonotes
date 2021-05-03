package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.entity.Note;

public interface NoteService {

	public void createNote(String token,NoteDto noteDto);
	
	public List<Note> getAllNotes(String token);
	
	public Note getNote(String token,Long noteId);
	
	public Note changeColour(String token,Long noteId,String colour);
	
	public Note pinNote(String token,Long noteId);
	
	public Note archiveNote(String token,Long noteId);
	
	public Note trashNote(String token,Long noteId);

	public void deleteNote(String token,Long noteId);
}
