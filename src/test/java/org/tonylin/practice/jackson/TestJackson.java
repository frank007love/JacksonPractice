package org.tonylin.practice.jackson;
import java.io.File;

import static org.junit.Assert.*;
import org.junit.Test;
import org.tonylin.practice.jackson.resolver.Animal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJackson {

	// https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
	@Test
	public void testReadObject() throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		User user = mapper.readValue(new File("testdata/user.txt"), User.class);
		assertEquals("user_passwd", user.getPasswd());
	}
	
	// http://www.baeldung.com/jackson-advanced-annotations
	@Test
	public void testResolver() throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		Animal animal = mapper.readValue(new File("testdata/cat.txt"), Animal.class);
		System.out.println(animal.getName());
	}
}
