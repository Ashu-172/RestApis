package com.mishra.api.BasicApi03.Response;

public class Greet {
	private int id;
	private String name;
	private String greeting;
	
	public void setId(int lId){
		id = lId;
	}
	
	public void setName(String lName){
		name =lName;
	}
	
	public void setGreeting(String lGreeting){
		greeting = lGreeting;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGreetiString() {
		return greeting;
	}
}
