package org.tonylin.practice.jackson.annotation.serializer;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Base64Text {
	@JacksonXmlText
	private String value;

	public Base64Text(String value) {
		this.value = value;
	}
	@JacksonXmlProperty(isAttribute=true,localName="encoding")
	public String getEncoding(){
		return "base64";
	}
	
	public String getValue(){
		return value;
	}
	
	@JsonIgnore
	public String getEncodingValue(){
	    return new String(Base64.getEncoder().encode(value.getBytes()));
	}
	
	public static Base64Text decode(String aEncryptValue){
	    if( aEncryptValue == null )
	        return null;
	    String decodeString = new String(Base64.getDecoder().decode(aEncryptValue));	   
	    return new Base64Text(decodeString);
	}
}