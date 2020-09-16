package com.fr.Agenda.AgendaX.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.Agenda.AgendaX.AgendaXApplication;
import com.fr.Agenda.AgendaX.dto.UserCreateDto;
import com.fr.Agenda.AgendaX.entity.User;

@SpringBootTest(classes = AgendaXApplication.class)
public class UserCreateDtoConverterTest {

	@Test
	public void convertDtoToEntity_shouldSatisfyAssertion() {
		UserCreateDto dto = new UserCreateDto();
		
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(1234);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");
		dto.setLaboratory("CNRS");
		
		User entity = UserCreateDtoConverter.MAPPER.convert(dto);
		
		assertNotNull(entity);
		assertThat(entity).hasFieldOrPropertyWithValue("email", "a@a.fr");
		assertThat(entity).hasFieldOrPropertyWithValue("lastName", "Murray");
		assertThat(entity).hasFieldOrPropertyWithValue("firstName", "billy");
		assertThat(entity).hasFieldOrPropertyWithValue("adress", "adresse");
		assertThat(entity).hasFieldOrPropertyWithValue("zipCode", 1234);
		assertThat(entity).hasFieldOrPropertyWithValue("city", "city");
		assertThat(entity).hasFieldOrPropertyWithValue("pwd", "azerty");
		assertThat(entity).hasFieldOrPropertyWithValue("phone", "012345678");
		assertThat(entity).hasFieldOrPropertyWithValue("laboratory", "CNRS");
		
	}
	
	
	@Test
	public void convertEntityToDto_shouldSatisfyAssertion() {
	
		User entity = new User();
		
		entity.setEmail("a@a.fr");
		entity.setLastName("Murray");
		entity.setFirstName("billy");
		entity.setAdress("adresse");
		entity.setZipCode(1234);
		entity.setCity("city");
		entity.setPwd("azerty");
		entity.setPhone("012345678");
		entity.setLaboratory("CNRS");
		
		UserCreateDto dto = UserCreateDtoConverter.MAPPER.convert(entity);
		
		assertNotNull(dto);
		assertThat(dto).hasFieldOrPropertyWithValue("email", "a@a.fr");
		assertThat(dto).hasFieldOrPropertyWithValue("lastName", "Murray");
		assertThat(dto).hasFieldOrPropertyWithValue("firstName", "billy");
		assertThat(dto).hasFieldOrPropertyWithValue("adress", "adresse");
		assertThat(dto).hasFieldOrPropertyWithValue("zipCode", 1234);
		assertThat(dto).hasFieldOrPropertyWithValue("city", "city");
		assertThat(dto).hasFieldOrPropertyWithValue("pwd", "azerty");
		assertThat(dto).hasFieldOrPropertyWithValue("phone", "012345678");
		assertThat(dto).hasFieldOrPropertyWithValue("laboratory", "CNRS");
		
	}
	
	@Test
	public void convertDtosToEntities_shouldSatisfyAssertion() {
		
		UserCreateDto dto = new UserCreateDto();
		
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(1234);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");
		dto.setLaboratory("CNRS");
		
		UserCreateDto dto2 = new UserCreateDto();
		
		dto2.setEmail("b@a.fr");
		dto2.setLastName("Burray");
		dto2.setFirstName("Milly");
		dto2.setAdress("adresse2");
		dto2.setZipCode(5678);
		dto2.setCity("city2");
		dto2.setPwd("azerty2");
		dto2.setPhone("876543210");
		dto2.setLaboratory("CNRS");
		
		ArrayList<UserCreateDto> dtos = new ArrayList<>();
		dtos.add(dto);
		dtos.add(dto2);
		
		
		
		List<User> entities = UserCreateDtoConverter.MAPPER.convert(dtos);
		
		assertThat(entities).isNotNull().hasSize(2);
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("email", "a@a.fr");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("lastName", "Murray");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("firstName", "billy");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("adress", "adresse");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("zipCode", 1234);
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("city", "city");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("pwd", "azerty");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("phone", "012345678");
		assertThat(entities.get(0)).isNotNull().hasFieldOrPropertyWithValue("laboratory", "CNRS");
		
		
		
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("email", "b@a.fr");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("lastName", "Burray");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("firstName", "Milly");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("adress", "adresse2");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("zipCode", 5678);
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("city", "city2");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("pwd", "azerty2");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("phone", "876543210");
		assertThat(entities.get(1)).isNotNull().hasFieldOrPropertyWithValue("laboratory", "CNRS");
	
	}
	
	@Test
	public void convertEntitiesToDtos_shouldSatisfyAssertion(){
		
		User entity = new User();
		
		entity.setEmail("a@a.fr");
		entity.setLastName("Murray");
		entity.setFirstName("billy");
		entity.setAdress("adresse");
		entity.setZipCode(1234);
		entity.setCity("city");
		entity.setPwd("azerty");
		entity.setPhone("012345678");
		entity.setLaboratory("CNRS");
		
		User entity2 = new User();
		
		entity2.setEmail("b@a.fr");
		entity2.setLastName("Burray");
		entity2.setFirstName("Milly");
		entity2.setAdress("adresse2");
		entity2.setZipCode(5678);
		entity2.setCity("city2");
		entity2.setPwd("azerty2");
		entity2.setPhone("876543210");
		entity2.setLaboratory("CNRS");
		
		List <User> entities = new ArrayList<>();
		entities.add(entity);
		entities.add(entity2);
		
		List<UserCreateDto> dtos = UserCreateDtoConverter.MAPPER.convert(entities);
		
		assertThat(dtos).isNotNull().hasSize(2);
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("email", "a@a.fr");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("lastName", "Murray");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("firstName", "billy");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("adress", "adresse");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("zipCode", 1234);
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("city", "city");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("pwd", "azerty");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("phone", "012345678");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("laboratory", "CNRS");
		
		
		
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("email", "b@a.fr");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("lastName", "Burray");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("firstName", "Milly");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("adress", "adresse2");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("zipCode", 5678);
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("city", "city2");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("pwd", "azerty2");
		assertThat(dtos.get(1)).isNotNull().hasFieldOrPropertyWithValue("phone", "876543210");
		assertThat(dtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("laboratory", "CNRS");
		
		
	}
}

