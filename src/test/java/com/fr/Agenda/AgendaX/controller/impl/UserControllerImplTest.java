package com.fr.Agenda.AgendaX.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fr.Agenda.AgendaX.AgendaXApplicationTests;
import com.fr.Agenda.AgendaX.constants.AbstractConstants;
import com.fr.Agenda.AgendaX.dto.UserCreateDto;
import com.fr.Agenda.AgendaX.dto.UserLoginDto;
import com.fr.Agenda.AgendaX.dto.UserUpdateDto;

// A refaire avec les bonne variables !!
public class UserControllerImplTest extends AgendaXApplicationTests {

	@Test
	@Sql(statements = "delete from customer where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValideUserExample_shouldReturnStatus200WithContentNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		try {
			UserCreateDto dto = new UserCreateDto();

			// prepare inputs
			dto.setEmail("a@a.fr");
			dto.setLastName("Murray");
			dto.setFirstName("billy");
			dto.setAdress("adresse");
			dto.setZipCode(123);
			dto.setCity("city");
			dto.setPwd("azerty");
			dto.setPhone("012345678");

			String json = mvc
					.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
							.content(mapper.writeValueAsString(dto)))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			UserCreateDto response = mapper.readValue(json, UserCreateDto.class);

			// verify result
			assertNotNull(response);
			assertThat(response).hasFieldOrPropertyWithValue("email", "a@a.fr");
			assertThat(response).hasFieldOrPropertyWithValue("lastName", "Murray");
			assertThat(response).hasFieldOrPropertyWithValue("firstName", "billy");
			assertThat(response).hasFieldOrPropertyWithValue("adress", "adresse");
			assertThat(response).hasFieldOrPropertyWithValue("zipCode", 123);
			assertThat(response).hasFieldOrPropertyWithValue("city", "city");
			assertThat(response).hasFieldOrPropertyWithValue("pwd", "azerty");
			assertThat(response).hasFieldOrPropertyWithValue("phone", "012345678");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'b@b.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveExistingUser_shouldReturnStatus200WithNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("b@b.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidUser_shouldReturnUserWithModification()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserUpdateDto dto = new UserUpdateDto();

		// prepare inputs
		dto.setId(1L);
		dto.setEmail("b@b.fr");
		dto.setLastName("Jean");
		dto.setFirstName("marc");
		dto.setAdress("adr");
		dto.setZipCode(145);
		dto.setCity("lyon");
		dto.setPwd("pmloik");
		dto.setPhone("987654321");

		String json = mvc
				.perform(put(AbstractConstants.User.UPDATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		UserUpdateDto response = mapper.readValue(json, UserUpdateDto.class);

		// verify result
		assertNotNull(response);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateDifferentIdUser_shouldNotReturnEntityWithIdNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		// prepare inputs
		dto.setId(50L);
		dto.setEmail("b@b.fr");
		dto.setLastName("Jean");
		dto.setFirstName("marc");
		dto.setAdress("adr");
		dto.setZipCode(145);
		dto.setCity("lyon");
		dto.setPwd("pmloik");
		dto.setPhone("987654321");

		String json = mvc
				.perform(put(AbstractConstants.User.UPDATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// verify result
		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteValidUser_shouldReturnTrue()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		dto.setId(1L);

		String json = mvc
				.perform(delete(AbstractConstants.User.DELETE_ID, 1L).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("true", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'v@v.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteNotExistingUser_shouldReturnTrue()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		dto.setId(145L);

		String json = mvc
				.perform(delete(AbstractConstants.User.DELETE_ID, 145L).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("false", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void EmptyListUser_shouldReturnEmptyList()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.READ_ALL_REAL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("[]", json);
	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListValidUser_shouldNotReturnEmptyList() throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.READ_ALL_REAL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].email", is("a@a.fr"))).andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(1, response.size());

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void setDeletedTrueExistingUser_shouldReturnTrue()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		String json = mvc
				.perform(put(AbstractConstants.User.SET_DELETED_TRUE, 1L).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("true", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void setDeletedTrueNotExistingUser_shouldReturnFalse()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		String json = mvc
				.perform(put(AbstractConstants.User.SET_DELETED_TRUE, 15L).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("false", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void setDeletedNotExistingUser_shouldReturnFalse()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		UserUpdateDto dto = new UserUpdateDto();

		String json = mvc
				.perform(put(AbstractConstants.User.SET_DELETED_TRUE, 15L).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("false", json);

	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'adresse', 0123, 'lyon', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Murray', 'adresse', 0123, 'roanne', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void List2SetDeletedTrueAnd1SetDeletedFalse_shouldReturnAListWith2Elements()
			throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.READ_DELETE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].city", is("lyon"))).andExpect(jsonPath("$[1].city", is("roanne"))).andReturn()
				.getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(2, response.size());

	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 0123, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'adresse', 0123, 'city', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Murray', 'adresse', 0123, 'city', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void List2SetDeletedTrueAnd1SetDeletedFalse_shouldReturnAListWith1Element()
			throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.READ_ALL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].city", is("city"))).andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(1, response.size());
	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'rue', 0123, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'rue', 0123, 'city', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Jean', 'adresse', 0123, 'city', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListOfUser_shouldReturnListOfUserByTheirLastName()
			throws UnsupportedEncodingException, Exception {

		String json = mvc
				.perform(get(AbstractConstants.User.GET_LASTNAME, "Jean").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].email", is("c@c.fr"))).andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(1, response.size());
	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'rue', 456, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'rue', 456, 'city', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Murray', 'adresse', 145, 'city', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListOfUser_shouldReturnListOfUserByTheirZipCode()
			throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.GET_ZIPCODE, 456L).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].email", is("a@a.fr"))).andExpect(jsonPath("$[1].email", is("b@b.fr")))
				.andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(2, response.size());

	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'rue', 0123, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'rue', 0123, 'city', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Murray', 'adresse', 0123, 'lyon', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListOfUser_shouldReturnListOfUserByTheirCity() throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.GET_CITY, "lyon").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].email", is("c@c.fr"))).andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(1, response.size());
	}

	@Test
	@Sql(statements = { "delete from customer",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'rue', 123, 'city', 'azerty', false)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (2, 'b@b.fr','Murray', 'rue', 123, 'city', 'azerty', true)",
			"insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (3, 'c@c.fr','Murray', 'adresse', 123, 'city', 'azerty', true)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListOfUser_shouldReturnListOfUserByTheirAdress()
			throws UnsupportedEncodingException, Exception {

		String json = mvc.perform(get(AbstractConstants.User.GET_ADRESS, "rue").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].email", is("a@a.fr"))).andExpect(jsonPath("$[1].email", is("b@b.fr")))
				.andReturn().getResponse().getContentAsString();

		List<UserUpdateDto> response = mapper.readValue(json, new TypeReference<List<UserUpdateDto>>() {
		});

		assertEquals(2, response.size());

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFalsePatternEmail_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("lapin");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullEmail_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail(null);
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testBlankEmail_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("    ");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullLastName_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName(null);
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullAdress_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress(null);
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullZipCode_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(null);
		dto.setCity("city");
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullCity_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity(null);
		dto.setPwd("azerty");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNullPwd_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd(null);
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testEmptyPwd_shouldReturnNullAndNoNewUser()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// prepare inputs
		UserCreateDto dto = new UserCreateDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setLastName("Murray");
		dto.setFirstName("billy");
		dto.setAdress("adresse");
		dto.setZipCode(123);
		dto.setCity("city");
		dto.setPwd("");
		dto.setPhone("012345678");

		String json = mvc
				.perform(post(AbstractConstants.User.CREATE).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);
	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUser_shouldReturnSuccesForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setPwd("azerty");

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		UserCreateDto response = mapper.readValue(json, UserCreateDto.class);

		assertNotNull(response);
		assertThat(response).hasFieldOrPropertyWithValue("email", "a@a.fr");
		assertThat(response).hasFieldOrPropertyWithValue("pwd", "azerty");

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUserWithFalsePatternEmail_shouldReturnFailForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail("test");
		dto.setPwd("azerty");

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUserWithNullEmail_shouldReturnFailForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail(null);
		dto.setPwd("azerty");

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUserWithBlankEmail_shouldReturnFailForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail("   ");
		dto.setPwd("azerty");

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUserWithNullPwd_shouldReturnFailForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setPwd(null);

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}

	@Test
	@Sql(statements = "insert into customer (id, email, last_name, adress, zip_code, city, pwd, deleted) values (1, 'a@a.fr','Murray', 'adresse', 123, 'city', 'azerty', false)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from customer", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginUserWithEmptyPwd_shouldReturnFailForLogin()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		// prepare inputs
		UserLoginDto dto = new UserLoginDto();

		// prepare inputs
		dto.setEmail("a@a.fr");
		dto.setPwd("");

		String json = mvc
				.perform(post(AbstractConstants.User.LOGIN).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", json);

	}
}