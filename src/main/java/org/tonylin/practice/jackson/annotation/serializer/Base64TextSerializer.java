package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.util.Base64;

import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.XmlSerializerProvider;

public class Base64TextSerializer extends StdSerializer<Base64Text> {
	private static final long serialVersionUID = 1L;

	public Base64TextSerializer() {
		super(Base64Text.class);
	}
	
	@Override
	public void serialize(Base64Text value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		ToXmlGenerator xgen = (ToXmlGenerator) jgen;
		
		xgen.writeStartObject();
		xgen.setNextIsAttribute(true);
		xgen.writeFieldName("type");
		xgen.writeString(value.getEncoding());
		xgen.setNextIsAttribute(false);
		
		xgen.setNextIsUnwrapped(true);
		xgen.writeFieldName("value");
		xgen.writeObject(value.getEncodingValue());
		xgen.setNextIsUnwrapped(false);
		
		xgen.writeEndObject();
	}

}
