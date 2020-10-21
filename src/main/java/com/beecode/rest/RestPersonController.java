package com.beecode.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beecode.entity.Email;
import com.beecode.entity.Person;
import com.beecode.entity.User;
import com.beecode.exceptions.DuplicateMailException;
import com.beecode.exceptions.InvalidMailException;
import com.beecode.interfaces.PersonService;
import com.beecode.interfaces.UserService;
import com.beecode.projection.PersonProjection;

@RestController
public class RestPersonController {	

	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/public/persons/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Person>> getAllPersons(){
		return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/custom/persons/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<PersonProjection>> getAllCustomPersons(){
		return new ResponseEntity<>(personService.getAllPersonProjection(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/count/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Long> getCountPersons(){
		return new ResponseEntity<>(personService.getPersonCount(), HttpStatus.OK);
	}
	
	//Register new User + Person embed
	@PostMapping(value = "/public/register/person", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> registerPerson(@RequestBody Person person) throws DuplicateMailException, InvalidMailException {
		//return new ResponseEntity<Person>(personService.createPerson(person), HttpStatus.CREATED);
		String email = person.getEmail();
		Optional<User> userVerification = userService.getUserByEmail(email);
		if(!userVerification.isPresent()) {
			if(Email.isValid(email)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(person));
			}else {
				throw new InvalidMailException();
			}
		}else {
			throw new DuplicateMailException();
		}
	}
	
	//Update person
	@PostMapping(value = "/public/update/person", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.updatePerson(person));
	}
}
