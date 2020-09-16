package com.fr.Agenda.AgendaX.converter;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fr.Agenda.AgendaX.dto.UserLoginDto;
import com.fr.Agenda.AgendaX.entity.User;

@Mapper
public interface UserLoginDtoConverter {


	UserLoginDtoConverter MAPPER = Mappers.getMapper(UserLoginDtoConverter.class);

	List<User> convert(ArrayList<UserLoginDto> dtos);
	
	User convert(UserLoginDto dto);
	
	UserLoginDto convert(User entity);
	
	List<UserLoginDto> convert(List<User> entities);
}
