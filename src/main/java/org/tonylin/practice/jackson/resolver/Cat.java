package org.tonylin.practice.jackson.resolver;

public class Cat implements Animal{
	private String type;
	private String name;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setType(String type){
		this.type = type;
	}
}
