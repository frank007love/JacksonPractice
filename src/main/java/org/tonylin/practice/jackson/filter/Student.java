package org.tonylin.practice.jackson.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("simpleFilter")
public class Student {
	@JsonProperty(value="id", required=true)
	private String id;
	
	@JsonProperty(value="passwd")
	@JsonOptionalProperty
	private String passwd;
	
	@JsonProperty(value="email")
	private String email;
	
	public Student() {
	}
	
	public String getEmail() {
		return email;
	}
	

	public void setEmail(String aEmail) {
		this.email = aEmail;
	}

	public String getID() {
		return id;
	}

	public void setID(String aID) {
		this.id = aID;
	}
	//@JsonOptionalProperty
	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String aPasswd) {
		this.passwd = aPasswd;
	}
}
