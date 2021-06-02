package one.digitalinnovation.personapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	@InjectMocks
	private PersonService personService;
	
	@Test
	void testeGivenPersonDTOThenReturnSavedMessage() {
		PersonDTO personDTO = PersonUtils.createFakePerson();
		Person expectedSavedPerson = PersonUtils.createFakeEntity();
		
		Mockito.when(personRepository.save(Mockito.any(Person.class)))
			.thenReturn(expectedSavedPerson);
		MessageResponseDTO expectedSucessMessage = MessageResponseDTO
				.builder()
				.message("Person created with ID "+ expectedSavedPerson.getId())
				.build();
		
		MessageResponseDTO successMessage = personService.createPerson(personDTO);
		
		assertEquals(expectedSucessMessage, successMessage);
	}
}
