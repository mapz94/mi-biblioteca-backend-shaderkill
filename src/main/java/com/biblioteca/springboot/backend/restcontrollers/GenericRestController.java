package com.biblioteca.springboot.backend.restcontrollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/biblio")


public class GenericRestController {
	@RequestMapping(value ="/**")
	public ResponseEntity<HttpStatus> retrieve(HttpServletRequest requestd){
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

}
