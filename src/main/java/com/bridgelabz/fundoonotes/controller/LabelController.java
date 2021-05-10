package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.LabelService;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	LabelService labelService;

	@PostMapping("/create")
	public ResponseEntity<Response> createLabel(@RequestHeader String token, @Valid @RequestBody LabelDto labelDto,
			BindingResult result) {
		if (result.hasErrors())
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(),
					result.getAllErrors().get(0).getDefaultMessage(), null), HttpStatus.UNPROCESSABLE_ENTITY);
		Label label = labelService.createLabel(token, labelDto);
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(),
				ApplicationConfig.getMessageAccessor().getMessage("300"), label), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<Response> getUserLabels(@RequestHeader String token) {

		List<Label> labels = labelService.getUserLabels(token);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("301"), labels),
				HttpStatus.OK);
	}

	@DeleteMapping("/{labelId}")
	public ResponseEntity<Response> deleteLabel(@RequestHeader String token, @PathVariable Long labelId) {

		labelService.deleteLabel(token, labelId);

		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), ApplicationConfig.getMessageAccessor().getMessage("302"), ""),
				HttpStatus.OK);
	}

	@PutMapping("/{labelId}")
	public ResponseEntity<Response> updateLabel(@RequestHeader String token, @PathVariable Long labelId,
			@Valid @RequestBody LabelDto labelDto,BindingResult result) {
		if (result.hasErrors())
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(),
					result.getAllErrors().get(0).getDefaultMessage(), null), HttpStatus.UNPROCESSABLE_ENTITY);
		Label label = labelService.updateLabel(token, labelId, labelDto);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),
				ApplicationConfig.getMessageAccessor().getMessage("303"), label), HttpStatus.OK);
		
	}
}
