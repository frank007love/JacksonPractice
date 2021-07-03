package org.tonylin.practice.jackson.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class IgnoreCaseBeanPropertyFilter extends SimpleBeanPropertyFilter {

	protected final Set<String> mPropertiesToInclude;
	
	public IgnoreCaseBeanPropertyFilter(List<String> aPropSet){
		this(aPropSet.toArray(new String[0]));
	}
	
	public IgnoreCaseBeanPropertyFilter(String ... aProps){
		mPropertiesToInclude = new HashSet<String>();
		for( String prop : aProps )
			mPropertiesToInclude.add(prop.toLowerCase());
	}
	
	@Override
	protected boolean include(BeanPropertyWriter aParamBeanPropertyWriter) {
		if( aParamBeanPropertyWriter.isRequired() ){
			return true;
		}
		return  mPropertiesToInclude.contains(aParamBeanPropertyWriter.getName().toLowerCase());
	}

	@Override
	protected boolean include(PropertyWriter aParamPropertyWriter) {
		if( aParamPropertyWriter.isRequired() )
			return true;
		return mPropertiesToInclude.contains(aParamPropertyWriter.getName().toLowerCase());
	}

}
