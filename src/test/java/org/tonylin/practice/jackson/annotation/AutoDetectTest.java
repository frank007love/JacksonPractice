package org.tonylin.practice.jackson.annotation;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

public class AutoDetectTest {
	@JsonAutoDetect(fieldVisibility=Visibility.NONE)
	public static class AutoDetectObject {
		private String mName ;

		public AutoDetectObject(){
			
		}
		private void setName(String aName){
			mName = aName;
		}
		
		public String getName(){
			return mName;
		}
	}
	@Test
	public void testJsonGenerator() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		VisibilityChecker<?> checker = mapper.getVisibilityChecker().
				withGetterVisibility(Visibility.NONE).
				withIsGetterVisibility(Visibility.NONE);
		mapper.setVisibility(checker);
		
		AutoDetectObject u = new AutoDetectObject();
		u.setName("test");
		
		String value = mapper.writeValueAsString(u);
		System.out.println(value);
		
//		AutoDetectObject newObj = mapper.readValue("{\"name2\":\"test\"}", AutoDetectObject.class);
//		System.out.println(newObj.getName());
	}
	
}
