package org.tonylin.practice.jackson;

import java.util.HashMap;
import java.util.Map;

import org.tonylin.practice.jackson.annotation.serializer.KeySerializer;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFilter("simpleFilter")
@JsonPropertyOrder({"passwd","email","id"})
public class Student {
	@JsonProperty(value="id")
	private String mID;
	
	@JsonProperty(value="passwd")
	private String mPasswd;
	
	@JsonProperty(value="email", required=false)
	private String mEmail;
	
	@JsonProperty(value="metadata")
	@JsonSerialize(keyUsing=KeySerializer.class)
	private Map<String, Object> mMetadata;
	
	public Student() {
		mMetadata = new HashMap<String, Object>();
		
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("$ARG4$", "789");
		tmp.put("$ARG5$", "111");
		
		mMetadata.put("$ARG1$", "123");
		mMetadata.put("$ARG2$", "456");
		mMetadata.put("$ARG3$", tmp);
	}
	
	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String aEmail) {
		this.mEmail = aEmail;
	}
	
	public String getID() {
		return mID;
	}

	public void setID(String aID) {
		this.mID = aID;
	}

	public String getPasswd() {
		return mPasswd;
	}

	public void setPasswd(String aPasswd) {
		this.mPasswd = aPasswd;
	}
}
