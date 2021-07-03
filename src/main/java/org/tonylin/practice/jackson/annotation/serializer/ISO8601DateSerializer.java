package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.format.ISODateTimeFormat;

public class ISO8601DateSerializer extends StdSerializer<Date> {
	private static final long serialVersionUID = 1L;

	public ISO8601DateSerializer() {
		super(Date.class);
	}

	// public static String convert(Date date, int aOffset){
	// DateTime dt = new DateTime(date.getTime()+aOffset, DateTimeZone.UTC);
	// return ISODateTimeFormat.dateTime().print(dt);
	// }
	//
	// public static String convert(Date date){
	// return convert(date, 0);
	// }

	@Override
	public void serialize(Date date, JsonGenerator json,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		
		DateFormat df = StdDateFormat.getISO8601Format(
				TimeZone.getTimeZone("UTC"), Locale.getDefault());
		String out = df.format(date);


		json.writeString(out);
	}

}