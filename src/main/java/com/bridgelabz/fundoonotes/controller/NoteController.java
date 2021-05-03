package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NoteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/note")
public class NoteController {

	@Autowired
	NoteService noteService;

	@PostMapping(value = "/create")
	public ResponseEntity<Response> createNote(@RequestHeader String token,@Valid @RequestBody NoteDto noteDto,BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), result.getAllErrors().get(0).getDefaultMessage(), null), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		noteService.createNote(token, noteDto);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("200"),""), HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation("api to get all notes related to a user")
	public ResponseEntity<Response> getAllNotes(@RequestHeader String token){
		List<Note> notes= noteService.getAllNotes(token);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("201"),notes),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{noteId}")
	public ResponseEntity<Response> getNote(@RequestHeader String token,@PathVariable Long noteId){
		
		Note note = noteService.getNote(token, noteId);
		
		return new  ResponseEntity<Response> (new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("202"), note),HttpStatus.OK);
	}
	
	@PutMapping(value = "/colour/{noteId}")
	public ResponseEntity<Response> changeColour( @RequestHeader String token,@PathVariable Long noteId,@RequestParam String colour){
		
		Note note = noteService.changeColour(token, noteId, colour);
		
		return new  ResponseEntity<Response> (new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("203"), note),HttpStatus.OK);
	}
	
	@PutMapping(value = {"/pin/{noteId}","/unpin/{noteId}"})
	public ResponseEntity<Response> pinNote(@RequestHeader String token,@PathVariable Long noteId,HttpServletRequest request){
		System.out.println(request.getRequestURI());
		Note note = noteService.pinNote(token, noteId);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("204"), note),HttpStatus.OK);
	}
	
	@PutMapping(value = {"/archive/{noteId}","/unarchive/{noteId}"})
	public ResponseEntity<Response> archiveNote(@RequestHeader String token,@PathVariable Long noteId){
		
		Note note = noteService.archiveNote(token, noteId);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("204"), note),HttpStatus.OK);
	}
	
	@PutMapping(value = {"/trash/{noteId}","/untrash/{noteId}"})
	public ResponseEntity<Response> trashNote(@RequestHeader String token,@PathVariable Long noteId){
		
		Note note = noteService.trashNote(token, noteId);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("204"), note),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{noteId}")
	public ResponseEntity<Response> deleteNote(@RequestHeader String token,@PathVariable Long noteId){
		
		noteService.deleteNote(token, noteId);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),ApplicationConfig.getMessageAccessor().getMessage("205") , ""), HttpStatus.OK);
	}
}
