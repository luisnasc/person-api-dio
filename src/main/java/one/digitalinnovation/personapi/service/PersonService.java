package one.digitalinnovation.personapi.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	private PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}	
	
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
				
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Person created with ID " + savedPerson.getId())
				.build();
	}

	public List<PersonDTO> listAll() {
		var allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO)
				.collect(Collectors.toList());
	}
		
	
}
