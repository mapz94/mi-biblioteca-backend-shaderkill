package com.biblioteca.springboot.backend;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalMessage {

	public static ResponseEntity<HashMap<String, HttpStatus>> notFound() {
		return new ResponseEntity<HashMap<String, HttpStatus>>(HttpStatus.NOT_FOUND);
	}
	public static ResponseEntity<HashMap<String, HttpStatus>> internalServerError() {
		return new ResponseEntity<HashMap<String, HttpStatus>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
