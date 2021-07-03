package org.tonylin.practice.jackson.annotation.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.tonylin.practice.jackson.annotation.Command;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class KeySerializerTest {
	@After
	public void teardown(){
		System.out.println();
	}
	
	@Test
	public void testJsonGenerator() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonGenerator generator = mapper.getFactory().createGenerator(System.out);

		Map<String, Object> metadata = new HashMap<String, Object>();
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("$ARG4$", "789");
		tmp.put("$ARG5$", "111");
		metadata.put("$ARG1$", "123");
		metadata.put("$ARG2$", "456");
		metadata.put("$ARG3$", tmp);
		
		Command cmd = new Command();
		cmd.setName("ping");
		cmd.setMetadata(metadata);

		System.out.println(mapper.writeValueAsString(cmd));
		
		generator.writeObject(cmd);
	}
	
	@Test
	public void testXmlGenerator() throws IOException {
		XmlMapper mapper = new XmlMapper();
		JsonGenerator generator = mapper.getFactory().createGenerator(System.out);
		
		Map<String, Object> metadata = new HashMap<String, Object>();
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("$ARG4$", "789");
		tmp.put("$ARG5$", "111");
		metadata.put("$ARG1$", "123");
		metadata.put("$ARG2$", "456");
		metadata.put("$ARG3$", tmp);
		
		Command cmd = new Command();
		cmd.setName("ping");
		cmd.setMetadata(metadata);

		generator.writeObject(cmd);
	}
}
