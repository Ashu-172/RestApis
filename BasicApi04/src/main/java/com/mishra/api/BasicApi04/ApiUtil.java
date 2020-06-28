package com.mishra.api.BasicApi04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ApiUtil {

	// Api Status
	public static final int STATUS_SUCCESS 				= 0;
	public static final int STATUS_FAILED 				= 1;

	// Error Codes
	public static final int SUCCESS 					= 2000;
	public static final int PARTIAL_SUCCESS				= 2001;
	public static final int FAILURE 					= 3000;
	public static final int SYSTEM_ERROR 				= 3001;
	public static final int INVALID_DOB_PARAM 			= 3002;
	public static final int INVALID_CLASS_PARAM 		= 3003;
	public static final int STUDENT_NOT_FOUND			= 3004;
	public static final int STUDENT_INFO_NOT_FOUND 		= 3005;
	public static final int NO_STUDENT_REGISTERED		= 3006;
	public static final int INVALID_SUBJECT_ID_PARAM	= 3007;
	public static final int INVALID_MARKS_PARAM			= 3008;
	public static final int INVALID_RESULT_PARAM		= 3009;
	public static final int NO_RESULT_AVAILABLE			= 3010;
	public static final int INVALID_EXAM_PARAM			= 3011;

	// Exam Types
	public static final int QUATERLY_EXAM 				= 101;
	public static final int HALF_YEARLY_EXAM 			= 102;
	public static final int YEARLY_EXAM 				= 103;
	
	//Result Status
	
	public static final int RESULT_PASS					= 1;
	public static final int RESULT_FAIL					= 0;
	
	public static enum Class{
		Class_First(101),
		Class_Second(102),
		Class_Third(103),
		Class_Fourth(104),
		Class_Fifth(105),
		Class_Sixth(106),
		Class_Seventh(107),
		Class_Eighth(108),
		Class_Ninth(109),
		Class_Tenth(110);
		
		private static final Map<Integer,Class> lookup 
			= new HashMap<Integer, Class>();
		static {
			for(Class c: EnumSet.allOf(Class.class))
				lookup.put(c.getClassId(), c);
		}
		private int class_id;
		private Class(int id) {
			this.class_id = id;
		}
		public int getClassId() {
			return class_id;
		}
		public static Class get(int id) {
			return lookup.get(id);
		}
		
	}
	
	public static enum Subject{
		English(1001),
		Hindi(1002),
		Science(1003),
		SocialScience(1004),
		Mathematics(1005);
		
		private int id;
		Subject(int id){
			this.id = id;
		}
		
		private int getSubId() {
			return id;
		}
		
		public static String getSubject(int id) {
			for(Subject sub: Subject.values()) {
				if(sub.getSubId()==id) {
					return sub.name();
				}
			}
			return null;
		}
	}

	public static String dataToJson(Object lResponse) {
		String jsonStr = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			return mapper.writeValueAsString(lResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public static LocalDate dateFormatter(String date) throws Exception {
		LocalDate localDate = null;
		DateTimeFormatter format;
		if (isValidDate(date)) {
			format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			localDate = LocalDate.parse(date, format);
		}
		return localDate;
	}

	public static boolean isValidDate(String date) throws ParseException {
		SimpleDateFormat basicFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat basicFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		basicFormat1.setLenient(false); // throw exception while parsing date if format is not dd/MM/yyyy
		basicFormat2.setLenient(false); // throw exception while parsing date if format is not dd-MM-yyyy
		try {
			basicFormat1.parse(date);
		} catch (ParseException e) {
			basicFormat2.parse(date);
		}
		return true;
	}
}
