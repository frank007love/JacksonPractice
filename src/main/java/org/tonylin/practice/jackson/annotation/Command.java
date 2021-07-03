package org.tonylin.practice.jackson.annotation;

import java.util.Map;

import org.tonylin.practice.jackson.annotation.serializer.KeySerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class Command {
	@JsonProperty(value="name")
	private String mName;
	
	
	@JsonSerialize(keyUsing=KeySerializer.class)
	@JsonProperty(value="metadata")
	private Map<String, Object> mMetadata;
	
	public Command() {

	}
	
	public void setMetadata(Map<String, Object> aMetadata){
		mMetadata = aMetadata;
	}
	

	
	public String getName() {
		return mName;
	}

	public void setName(String aName) {
		this.mName = aName;
	}
}
