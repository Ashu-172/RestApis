package com.mishra.api.basic01;

import static spark.Spark.get;

public class ApiServer {

	public static void main(String[] args) {
		
		get("/hello",(req,res)-> "Hello World..!");
	}

}
