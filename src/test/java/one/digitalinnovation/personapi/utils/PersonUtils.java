package one.digitalinnovation.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

public class PersonUtils {
	
	private static final String FIRST_NAME = "Luis";
	private static final String LAST_NAME = "Nascimento";
	private static final String CPF_NUMBER = "272.288.080-61";
	private static final long PERSON_ID = 1L;
	private static final LocalDate BIRTH_DATE = LocalDate.of(2000, 5, 2);
	
	public static PersonDTO createFakePerson() {
		return PersonDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("05-05-2000")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}
	
	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}	

}
