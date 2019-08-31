package com.springboot.example.springbootdemoproject.model;

/**
 *
 * 
 * 
 * The class defines the constants in the application.
 *
 * 
 * 
 */
public class ApplicationConstants {
	// Constants for Errors
	public static final String INVALID_TOPPER_INDEX_MESSAGE = "Invalid Topper Index";
	public static final String INVALID_EXAM_CODE_MESSAGE = "Exam code should be P or M or C only";
	public static final String INVALID_TOPPER_INDEX_DETAIL = "Topper Index cannot be zero or greater than 5";
	public static final String IO_EXCEPTION_DETAIL = "IOException caught while handling InvalidExamCodeException";
	// Constants for Exam Codes
	public static final String EXAM_CODE_PHYSICS = "P";
	public static final String EXAM_CODE_CHEMISTRY = "C";
	public static final String EXAM_CODE_MATHS = "M";
	// Constants for URI
	public static final String URI_GET_ALL_TOPPERS = "http://localhost:8080/examresults/toppers";
}
