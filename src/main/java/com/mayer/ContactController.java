package com.mayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mayer.domain.Data;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping("/retrieve/{id}")
	public ResponseEntity<Data> getData(@PathVariable int id) {
		Data data = contactService.findById(id);

		return new ResponseEntity<Data>(data, HttpStatus.OK);
	}

	@RequestMapping( value="/save", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Data> saveData(@RequestBody Data data) {
		contactService.save(data);
		System.out.println("data created"+ data);
		return new ResponseEntity<Data>(data, HttpStatus.CREATED);
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public ResponseEntity<List<Data>> getAll() {
		List<Data> listOfData = contactService.getAll();
		if (listOfData.isEmpty()) {
			return new ResponseEntity<List<Data>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Data>>(HttpStatus.OK);
		}
	}
}
