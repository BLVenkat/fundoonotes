package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.NoteImage;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.repository.NoteImageRespository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utils.S3Service;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Service
public class NoteServiceImp implements NoteService {

	@Autowired
	private NoteRepository noteRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private S3Service s3Service;
	
	
	@Autowired
	private NoteImageRespository noteImageRepo;

	@Override
	@CachePut("notes")
	public Note createNote(String token, NoteDto noteDto) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = new Note();
		// can use modelmapper also
		BeanUtils.copyProperties(noteDto, note);
		List<Note> notes = user.getNotes();
		Note savedNote = noteRepo.save(note);
		notes.add(savedNote);
		 userRepo.save(user);
		 return savedNote;
	}

	public User getUser(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new FundooException(HttpStatus.NOT_FOUND.value(),
				ApplicationConfig.getMessageAccessor().getMessage("1")));
	}

	@Override
	//@CachePut("notes")
	@Cacheable("notes")
	public List<Note> getAllNotes(String token) {
		User user = getUser(tokenService.decodeToken(token));
		return user.getNotes();
	}

	@Override
	public Note getNote(String token, Long noteId) {
		User user = getUser(tokenService.decodeToken(token));
		return getNote(user,noteId);
	}

	@Override
	public Note changeColour(String token, Long noteId, String colour) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		note.setColour(colour);
		return noteRepo.save(note) ;
	}
	
	public Note getNote(User user,Long noteId) {
		return user.getNotes().stream().filter(note -> note.getId().equals(noteId)).findFirst()
				.orElseThrow(() -> new FundooException(HttpStatus.NOT_FOUND.value(),
						ApplicationConfig.getMessageAccessor().getMessage("2")));
	}

	@Override
	public Note pinNote(String token, Long noteId) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		if(note.getPin().equals(true)) {
			note.setPin(false);
		}
		else {
			note.setPin(true);
		}
		
		return	noteRepo.save(note);
		
	}

	@Override
	public Note archiveNote(String token, Long noteId) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		if(note.getArchive().equals(true))
			note.setArchive(false);
		else
			note.setArchive(true);
		return noteRepo.save(note);
	}

	@Override
	public Note trashNote(String token, Long noteId) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		if(note.getTrash().equals(true))
			note.setTrash(false);
		else
			note.setTrash(true);
		return noteRepo.save(note);
	}

	@Override
	
	public void deleteNote(String token, Long noteId) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		 noteRepo.delete(note);
	}

	@Override
	public String addImage(String token, Long noteId, MultipartFile file) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = getNote(user, noteId);
		String key = s3Service.fileUpload(file, "note_images", note.getId().toString());
		NoteImage noteImage = new NoteImage();
		noteImage.setUrl(key);
		NoteImage savedNoteImage = noteImageRepo.save(noteImage);
		note.getImages().add(savedNoteImage);
		noteRepo.save(note);
		return key;
	}
	
}
