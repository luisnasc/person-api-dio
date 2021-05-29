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
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
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
		return createMessageResponse(savedPerson.getId(), "Created person with ID: ");
	}

	public List<PersonDTO> listAll() {
		var allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		
		//Person person = personRepository.findById(id)
			//.orElseThrow(() -> new PersonNotFoundException(id));
		
		Person person = verifyIfExists(id);
		
//		Optional<Person> optionalPerson =  personRepository.findById(id);
//		if(optionalPerson.isEmpty()) {
//			throw new PersonNotFoundException(id);
//		}
//		return personMapper.toDTO(optionalPerson.get());
		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated person with ID: ");
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
		
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id)  );
	}
}
