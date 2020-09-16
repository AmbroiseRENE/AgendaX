package com.fr.Agenda.AgendaX.converter;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fr.Agenda.AgendaX.dto.UserUpdateDto;
import com.fr.Agenda.AgendaX.entity.User;

@Mapper
public interface UserUpdateDtoConverter {


	UserUpdateDtoConverter MAPPER = Mappers.getMapper(UserUpdateDtoConverter.class);

	User convert(UserUpdateDto dto);
	
	List<User> convert(ArrayList<UserUpdateDto> dtos);

	UserUpdateDto convert(User entity);
	
	List<UserUpdateDto> convert(List<User> entities);
	
}
