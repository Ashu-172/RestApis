package com.mishra.api.BasicApi04;

import static spark.Spark.path;
import static spark.Spark.get;
import static spark.Spark.post;
public class ApiServer {
	
	public static void main(String[] args) {
		
		ApiService apiService = new ApiService();
		path("/school",()->{
			path("/student",()->{
				post("",(request,response)->{
					return apiService.addStudent(request,response);
				});
				
				get("",(request,response)->{
					return apiService.getStudent(request,response);
				});
				
				get("/class",(request,response)->{
					return apiService.getClassStudents(request,response);
				});
			});
			
			path("/result",()->{
				post("",(request,response)->{
					return apiService.addSubjectResult(request,response);
				});
				
				get("",(request,response)->{
					return apiService.getResult(request,response);
				});
			});
		});
	}

}
