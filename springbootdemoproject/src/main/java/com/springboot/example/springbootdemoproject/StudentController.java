package com.springboot.example.springbootdemoproject;

import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_CHEMISTRY;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_MATHS;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_PHYSICS;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.INVALID_EXAM_CODE_MESSAGE;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.INVALID_TOPPER_INDEX_MESSAGE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.example.springbootdemoproject.model.Exam;
import com.springboot.example.springbootdemoproject.model.InvalidExamCodeException;
import com.springboot.example.springbootdemoproject.model.InvalidTopperIndexException;
import com.springboot.example.springbootdemoproject.model.Student;
import com.springboot.example.springbootdemoproject.service.StudentExamResultsService;

/**
 * 
 * The class handles the application requests and provides responses.
 *
 * 
 * 
 */
@RestController
@RequestMapping("/examresults")
public class StudentController {
	@Autowired
	private StudentExamResultsService studentExamResultService;

	/**
	 * 
	 * Gets the Student details and his score, for the given topper index
	 *
	 * 
	 * 
	 * Note: {@literal @}GetMapping is a composed annotation that acts as a
	 * 
	 * shortcut for {@literal @}RequestMapping(method = RequestMethod.GET)
	 *
	 * 
	 * 
	 * @param topperIndex
	 * 
	 * @return the Student
	 * 
	 * @throws InvalidTopperIndexException
	 * 
	 */
	@GetMapping(value = "/studenttopper/{topperIndex}")
	public Student getStudentScore(@PathVariable("topperIndex") int topperIndex) throws InvalidTopperIndexException {
		if (topperIndex == 0 || topperIndex > 5) {
			throw new InvalidTopperIndexException(INVALID_TOPPER_INDEX_MESSAGE);
		}
		return studentExamResultService.getStudentDetails(--topperIndex);
	}

	/**
	 * 
	 * Gets the highest score for the exam, for the given exam code.
	 *
	 * 
	 * 
	 * @param examCode
	 * 
	 * @return the exam
	 * 
	 * @throws InvalidExamCodeException
	 * 
	 */
	@RequestMapping(value = "/exam/{examCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Exam getHighestScoreForExam(@PathVariable("examCode") String examCode) throws InvalidExamCodeException {
		if (EXAM_CODE_PHYSICS.equalsIgnoreCase(examCode) || EXAM_CODE_MATHS.equalsIgnoreCase(examCode)
				|| EXAM_CODE_CHEMISTRY.equalsIgnoreCase(examCode)) {
			return studentExamResultService.getHighestScoreForExam(examCode);
		} else {
			throw new InvalidExamCodeException();
		}
	}

	/**
	 * 
	 * Gets the list of all Student topper details and their respective scores
	 *
	 * 
	 * 
	 * @return the list of students
	 * 
	 */
	@RequestMapping(value = "/toppers", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public List<Student> getAllStudentToppers() {
		return studentExamResultService.getAllStudentDetails();
	}

	/**
	 * 
	 * Handles the InvalidExamCodeException
	 *
	 * 
	 * 
	 * @param response
	 * 
	 * @throws IOException
	 * 
	 */
	@ExceptionHandler(InvalidExamCodeException.class)
	public void handleExamException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), INVALID_EXAM_CODE_MESSAGE);
	}
}
