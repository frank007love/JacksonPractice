package org.tonylin.practice.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"email","mail"})
public class Student {
	@JsonProperty(value="id")
	private String mID;
	
	@JsonProperty(value="passwd")
	private String mPasswd;
	
	@JsonProperty(value="mail")
	@JsonIgnore
	private String mEmail;
	
	@JsonProperty(value="metadata")
	@JsonIgnoreProperties({"test1","test2"})
	private Map<String, Object> mMetadata;
	
	public Student() {
		mMetadata = new HashMap<String, Object>();
		
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("test1", "789");
		tmp.put("test2", "111");
		tmp.put("test3", "222");
		
		mMetadata.put("test1", "123");
		mMetadata.put("test2", "456");
		mMetadata.put("test3", tmp);
	}
	
	@JsonIgnore
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
