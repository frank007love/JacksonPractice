package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class Base64StringSerializer extends StdSerializer<String> {
	private static final long serialVersionUID = 1L;

	public Base64StringSerializer() {
		super(String.class);
	}
	
	@Override
	public void serialize(String value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		String encodingVal = new String(Base64.getEncoder().encode(value.getBytes()));
		if( jgen instanceof ToXmlGenerator ) {
			ToXmlGenerator xgen = (ToXmlGenerator) jgen;
			
			xgen.writeStartObject();
			xgen.setNextIsAttribute(true);
			xgen.writeFieldName("type");
			xgen.writeString("base64");
			xgen.setNextIsAttribute(false);
			
			xgen.setNextIsUnwrapped(true);
			xgen.writeFieldName("value");
			xgen.writeObject(encodingVal);
			xgen.setNextIsUnwrapped(false);
			
			xgen.writeEndObject();
		} else {
			jgen.writeString(encodingVal);	
		}
	}

}
