package com.mishra.api.basic02;

import static spark.Spark.get;
public class ApiServer {

	public static void main(String[] args) {

		get("/hello/:name",(req,res)->"hello "+req.params(":name")+"..! How are you doing..?");
	}

}
