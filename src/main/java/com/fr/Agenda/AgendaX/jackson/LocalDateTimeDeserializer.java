package com.fr.Agenda.AgendaX.jackson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserialization of java.time.LocalDateTime "dd/MM/yyyy"
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {

		return LocalDateTime.parse(parser.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}
