package com.springboot.example.springbootdemoproject.Exception;

import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.INVALID_TOPPER_INDEX_DETAIL;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.IO_EXCEPTION_DETAIL;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.example.springbootdemoproject.model.ExceptionResponse;
import com.springboot.example.springbootdemoproject.model.InvalidTopperIndexException;

/**
 *
 * 
 * 
 * The class handles the unhandled Business Exceptions thrown in the controller.
 *
 * 
 * 
 */
@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * 
	 * Handles the InvalidTopperIndexException
	 *
	 * 
	 * 
	 * @param studentExaminationResultsException
	 * 
	 * @return ResponseEntity object
	 * 
	 */
	@ExceptionHandler(InvalidTopperIndexException.class)
	public final ResponseEntity<ExceptionResponse> buildResponseEntityForInvalidTopperException(
			InvalidTopperIndexException studentExaminationResultsException) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(studentExaminationResultsException.getMessage(),
				INVALID_TOPPER_INDEX_DETAIL);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * Handles the IOException
	 *
	 * 
	 * 
	 * @param studentExaminationResultsException
	 * 
	 * @return ResponseEntity object
	 * 
	 */
	@ExceptionHandler(IOException.class)
	public final ResponseEntity<ExceptionResponse> buildResponseEntityForIOException(
			InvalidTopperIndexException studentExaminationResultsException) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(studentExaminationResultsException.getMessage(),
				IO_EXCEPTION_DETAIL);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}