package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class Base64StringDeserializer extends StdDeserializer<String> {

		private static final long serialVersionUID = 1L;

		public Base64StringDeserializer() {
	        super(Base64Text.class);
	    }

		@Override
		public String deserialize(JsonParser parser, DeserializationContext context)
				throws IOException, JsonProcessingException {
			String text = parser.getValueAsString();
			
			return new String(Base64.getDecoder().decode(text));
		}
}