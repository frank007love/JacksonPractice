package org.tonylin.practice.jackson.annotation;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * http://wiki.fasterxml.com/JacksonFeatureJsonFilter
 * 
 * @author TonyLin
 */
public class JsonIgnoreTest {

	@Before
	public void setup() {
		
	}

	@After
	public void teardown() {
		System.out.println();
	}

	@Test
	public void testJsonGenerator() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonGenerator generator = mapper.getFactory().createGenerator(
				System.out);

		Student u = new Student();
		u.setID("user");
		u.setPasswd("123456");

		generator.writeObject(u);
	}

	@Test
	public void testXmlGenerator() throws IOException {
		XmlMapper mapper = new XmlMapper();
		
		JsonGenerator generator = mapper.getFactory().createGenerator(
				System.out);

		Student u = new Student();
		u.setID("user");
		u.setPasswd("123456");

		generator.writeObject(u);
	}
}
