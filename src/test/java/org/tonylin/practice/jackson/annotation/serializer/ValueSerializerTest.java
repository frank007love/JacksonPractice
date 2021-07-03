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

public class ValueSerializerTest {
	
	public static class Event2 {
//		@JsonSerialize(using=ISO8601DateSerializer.class)
//		@JsonDeserialize(using=ISO8601DateDeserializer.class)
		private Date date;
		
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
	
	public static class Event {
		@JsonSerialize(using=ISO8601DateSerializer.class)
		@JsonDeserialize(using=ISO8601DateDeserializer.class)
		private Date date;
		
		private Base64Text message;
		
		public Date getDate(){
			return date;
		}
		
		public void setDate(Date date){
			this.date = date;
		}
		
		public Base64Text getMessage(){
			return message;
		}
		
		public void setMessage(Base64Text message){
			this.message = message;
		}
		
		public void setMessage2(String message){
			this.message = new Base64Text(message);
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
		e.setMessage2(msg);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event newEvent = mapper.readValue(ret, Event.class);
		assertEquals(msg, newEvent.getMessage().getValue());
	}
	
	@Test
	public void testUnicodeXml() throws Exception {
		XmlMapper mapper = new XmlMapper();
		
		
		SimpleModule m = new SimpleModule();
		m.addSerializer(Base64Text.class, new Base64TextSerializer());
		m.addDeserializer(Base64Text.class, new Base64TextDeserializer());
		mapper.registerModule(m);

		Event e = new Event();
		String msg = "test" + (char) 1 + (char) 2 + (char) 3;
		e.setMessage2(msg);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event newEvent = mapper.readValue(ret, Event.class);
		assertEquals(msg, newEvent.getMessage().getValue());
	}
	
	@Test
	public void testDateWithAnnotation() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Date d = new Date();
		Event2 e = new Event2();
		e.setDate(d);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event2 newEvent = mapper.readValue(ret, Event2.class);
		assertEquals(d.getTime(), newEvent.getDate().getTime());
	}
	
	@Test
	public void testDateWithModule() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule m = new SimpleModule();
		m.addSerializer(Date.class, new ISO8601DateSerializer());
		m.addDeserializer(Date.class, new ISO8601DateDeserializer());
		
		mapper.registerModule(m);
		
		Date d = new Date();
		Event2 e = new Event2();
		e.setDate(d);

		String ret = mapper.writeValueAsString(e);
		System.out.println(ret);
		Event2 newEvent = mapper.readValue(ret, Event2.class);
		assertEquals(d.getTime(), newEvent.getDate().getTime());
	}
	
	/**
<Event><date/><message type="base64">dGVzdAECAw==</message></Event>
{"date":"2016-03-09T16:36:29.581+0000","message":null}
{"date":null,"message":"test\u0001\u0002\u0003"}
	 * 
	 * Convert the value  fields with special char @JsonSerialize(using=ISO8601DateSerializer.class)
		@JsonDeserialize(using=ISO8601DateDeserializer.class)
	 * 
	 * 
	 */
	
}
