package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class JsonBase64TextSerializer extends StdSerializer<Base64Text> {
	protected JsonBase64TextSerializer() {
		super(Base64Text.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Base64Text value, JsonGenerator gen,
			SerializerProvider provider) throws IOException {
		gen.writeString(value.getValue());
	}

}
