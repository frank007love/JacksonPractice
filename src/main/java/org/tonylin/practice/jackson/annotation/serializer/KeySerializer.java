package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class KeySerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		gen.writeFieldName(value.replaceAll("\\$", ""));
	}
}
