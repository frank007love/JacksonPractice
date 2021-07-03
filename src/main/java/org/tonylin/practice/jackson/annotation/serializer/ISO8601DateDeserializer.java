package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;

public class ISO8601DateDeserializer extends StdDeserializer<Date> {

	private static final long serialVersionUID = 1L;

	public ISO8601DateDeserializer() {
        super(Date.class);
    }

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		try {

			DateFormat df = StdDateFormat.getISO8601Format(
					TimeZone.getTimeZone("UTC"), Locale.getDefault());
			return df.parse(parser.getText());
		} catch (Exception e) {
			return null;
		}
	}
}