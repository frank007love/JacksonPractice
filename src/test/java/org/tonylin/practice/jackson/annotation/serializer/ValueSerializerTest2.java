package org.tonylin.practice.jackson.annotation.serializer;

import java.util.Base64;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializerModifier;

public class ValueSerializerTest2 {
	
	
	
	public static class Event {
		private Date date;
		
		@JsonSerialize(using=Base64StringSerializer.class)
		@JsonDeserialize(using=Base64StringDeserializer.class)
		private String message;
		
		public Date getDate(){
			return date;
		}
		
		public void setDate(Date date){
			this.date = date;
		}
		
		public String getMessage(){
			return message;
		}
		
		public void setMessage(String message){
			this.message = message;
		}
	}
	
	@Test
	public void testUnicodeJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule m = new SimpleModule("test");
		m.addSerializer(Base64Text.class, new JsonBase64TextSerializer());
		mapper.registerModule(m);
		
		Event e = new Event();
		String msg = "test" + (char) 1 + (char) 2 + (char) 3;
		e.setMessage(msg);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event newEvent = mapper.readValue(ret, Event.class);
		assertEquals(msg, newEvent.getMessage());
	}
	
	@Test
	public void testUnicodeXml() throws Exception {
		XmlMapper mapper = new XmlMapper();

		Event e = new Event();
		String msg = "test" + (char) 1 + (char) 2 + (char) 3;
		e.setMessage(msg);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event newEvent = mapper.readValue(ret, Event.class);
		assertEquals(msg, newEvent.getMessage());
	}
	
	
}
