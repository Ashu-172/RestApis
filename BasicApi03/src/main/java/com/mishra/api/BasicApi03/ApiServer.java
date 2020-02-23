package com.mishra.api.BasicApi03;

import static spark.Spark.before;
import static spark.Spark.path;

import java.util.concurrent.atomic.AtomicInteger;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mishra.api.BasicApi03.Response.Greet;

public class ApiServer {
	
	public static void main(String[] args) {
		Greet greet = new Greet();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		AtomicInteger count = new AtomicInteger(1000);
		
		
		before((request,response)->{
			greet.setId(count.incrementAndGet());
			greet.setGreeting("Hello World..!");
		});
		path("/hello",()->{
			before("/:name",(request,response)->{
				greet.setGreeting("Hello "+request.params(":name")+"..!");
			});
			get("",(request, response)->{
				greet.setName("User");
				return gson.toJson(greet);
			});
			get("/:name",(request,response)->{
				greet.setName(request.params(":name"));
				return gson.toJson(greet);
			});
			
		});
	}

}

