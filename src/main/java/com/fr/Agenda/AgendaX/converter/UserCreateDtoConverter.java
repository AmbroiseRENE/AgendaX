package com.fr.Agenda.AgendaX.converter;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fr.Agenda.AgendaX.dto.UserCreateDto;
import com.fr.Agenda.AgendaX.entity.User;

@Mapper
public interface UserCreateDtoConverter {

	UserCreateDtoConverter MAPPER = Mappers.getMapper(UserCreateDtoConverter.class);

	User convert(UserCreateDto dto);

	List<User> convert(ArrayList<UserCreateDto> dtos);

	UserCreateDto convert(User entity);

	List<UserCreateDto> convert(List<User> entities);
}
