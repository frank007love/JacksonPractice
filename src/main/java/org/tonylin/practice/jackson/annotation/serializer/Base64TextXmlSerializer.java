package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializer;

public class Base64TextXmlSerializer extends XmlBeanSerializer {
	private static final long serialVersionUID = 1L;

	public Base64TextXmlSerializer(BeanSerializerBase src) {
		super(src);
	}
	
	@Override
	public void serializeWithType(Object bean, JsonGenerator gen,
			SerializerProvider provider, TypeSerializer typeSer)
			throws IOException {
		super.serializeWithType(bean, gen, provider, typeSer);
	}
	
	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		Object target = null;
		System.out.println("shit");
		if( value instanceof Base64Text ) {
			String encodingText = Base64.getEncoder().encodeToString(((Base64Text)value).getValue().getBytes());
			Base64Text encodingValue = new Base64Text(encodingText);
			target = encodingValue;
		} else {
			target = value;
		}
		
		super.serialize(target, jgen, provider);
	}

}
