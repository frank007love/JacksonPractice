package org.tonylin.practice.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"passwd","email","id"})
public class User {
	@JsonProperty(value="id")
	private String mID;
	
	@JsonProperty(value="passwd")
	private String mPasswd;
	
	@JsonProperty(value="email")
	private String mEmail;
	

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
