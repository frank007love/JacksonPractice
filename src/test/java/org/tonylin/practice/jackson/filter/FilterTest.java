package org.tonylin.practice.jackson.filter;

import java.io.IOException;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * http://wiki.fasterxml.com/JacksonFeatureJsonFilter
 * 
 * @author TonyLin
 */
public class FilterTest {
	// SimpleBeanPropertyFilter.filterOutAllExcept
	// SimpleBeanPropertyFilter.serializeAll(properties)
	// SimpleBeanPropertyFilter.serializeAllExcept(null);
	
	@Before
	public void setup() {
		
	}

	@After
	public void teardown() {
	}
	
	@Test
	public void testSerializeAllExcept() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		FilterProvider simpleFilter = new SimpleFilterProvider().addFilter(
				"simpleFilter", SimpleBeanPropertyFilter.serializeAllExcept("email"));
		mapper.setFilterProvider(simpleFilter);
		
		Student student = new Student();
		student.setID("user");
		student.setPasswd("123456");
		student.setEmail("test@hotmail.com");

		String ret = mapper.writeValueAsString(student);
		Student newStudent = mapper.readValue(ret, Student.class);
		assertEquals("user", newStudent.getID());
		assertEquals("123456", newStudent.getPasswd());
		assertNull(newStudent.getEmail());
	}

	@Test
	public void testFilterOutAllExcept() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		FilterProvider simpleFilter = new SimpleFilterProvider().addFilter(
				"simpleFilter", SimpleBeanPropertyFilter.filterOutAllExcept("email"));
		mapper.setFilterProvider(simpleFilter);
		

		Student student = new Student();
		student.setID("user");
		student.setPasswd("123456");
		student.setEmail("test@hotmail.com");

		String ret = mapper.writeValueAsString(student);
		Student newStudent = mapper.readValue(ret, Student.class);
		assertEquals("test@hotmail.com", newStudent.getEmail());
		assertNull(newStudent.getID());
		assertNull(newStudent.getPasswd());
	}
	
	@Test
	public void testIgnoreCaseFilter() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		
		FilterProvider simpleFilter = new SimpleFilterProvider().addFilter(
				"simpleFilter", new IgnoreCaseBeanPropertyFilter("EMAIL"));
		mapper.setFilterProvider(simpleFilter);
		Student student = new Student();
		student.setID("user");
		student.setPasswd("123456");
		student.setEmail("test@hotmail.com");
		
		String ret = mapper.writeValueAsString(student);
		Student newStudent = mapper.readValue(ret, Student.class);
		assertEquals("user", newStudent.getID());
		assertEquals("test@hotmail.com", newStudent.getEmail());
		assertNull(newStudent.getPasswd());
	}
	
	@Test
	public void testOptionalPropFilter() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		
		FilterProvider simpleFilter = new SimpleFilterProvider().addFilter(
				"simpleFilter", new OptionalBeanPropertyFilter());
		mapper.setFilterProvider(simpleFilter);
		
		Student student = new Student();
		student.setID("user");
		student.setPasswd("123456");
		student.setEmail("test@hotmail.com");
		
		String ret = mapper.writeValueAsString(student);
		Student newStudent = mapper.readValue(ret, Student.class);
		assertEquals("user", newStudent.getID());
		assertEquals("test@hotmail.com", newStudent.getEmail());
		assertNull(newStudent.getPasswd());
	}
}
