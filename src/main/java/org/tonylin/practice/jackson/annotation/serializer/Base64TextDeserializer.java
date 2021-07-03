package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class Base64TextDeserializer extends StdDeserializer<Base64Text> {

		private static final long serialVersionUID = 1L;

		public Base64TextDeserializer() {
	        super(Base64Text.class);
	    }

		@Override
		public Base64Text deserialize(JsonParser parser, DeserializationContext context)
				throws IOException, JsonProcessingException {
			String text = parser.getValueAsString();
			Base64Text encodingValue = Base64Text.decode(text);
			return encodingValue;
		}
}