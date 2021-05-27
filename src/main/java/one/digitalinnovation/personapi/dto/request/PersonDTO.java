package one.digitalinnovation.personapi.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Phone;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String lastName;

	@NotEmpty
	@CPF
	private String cpf;
	
	private String birthDate;
	
	@NotEmpty
	@Valid // Valida as anotações da classe PhoneDTO
	private List<PhoneDTO> phones;

}
