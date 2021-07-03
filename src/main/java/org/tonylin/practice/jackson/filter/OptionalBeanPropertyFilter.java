package org.tonylin.practice.jackson.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class OptionalBeanPropertyFilter extends SimpleBeanPropertyFilter {
	
	public OptionalBeanPropertyFilter(){

	}
	
	@Override
	protected boolean include(BeanPropertyWriter aParamBeanPropertyWriter) {
		JsonOptionalProperty annotation = aParamBeanPropertyWriter.getAnnotation(JsonOptionalProperty.class);
		if( annotation != null ) {
			return !annotation.optinal();
		}
		return true;
	}

	@Override
	protected boolean include(PropertyWriter aParamPropertyWriter) {
		
		if( aParamPropertyWriter instanceof BeanPropertyWriter ) {
			return this.include((BeanPropertyWriter)aParamPropertyWriter);
		}
		
		JsonOptionalProperty annotation = aParamPropertyWriter.getAnnotation(JsonOptionalProperty.class);
		if( annotation != null ) {
			return !annotation.optinal();
		}
		return true;
	}

}
