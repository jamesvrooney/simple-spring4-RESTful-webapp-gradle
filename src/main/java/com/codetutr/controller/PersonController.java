package com.codetutr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codetutr.domain.Person;
import com.codetutr.service.PersonService;

@RestController
@RequestMapping("api")
public class PersonController {
	
	PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping("person/random")
	public Person randomPerson() {
		return personService.getRandom();
	}
	
	@RequestMapping("person/{id}")
	public Person getById(@PathVariable Long id) {
		return personService.getById(id);
	}
	
	/* same as above method, but is mapped to
	 * /api/person?id= rather than /api/person/{id}
	 */
	@RequestMapping(value="person", params="id")
	public Person getByIdFromParam(@RequestParam("id") Long id) {
		return personService.getById(id);
	}
	
	/**
	 * Saves new person. Spring automatically binds the name
	 * and age parameters in the request to the person argument
	 * @param person
	 * @return String indicating success or failure of save
	 */
	@RequestMapping(value="person", method=RequestMethod.POST)
	public String savePerson(Person person) {
		personService.save(person);
		return "Saved person: " + person.toString();
	}
}
