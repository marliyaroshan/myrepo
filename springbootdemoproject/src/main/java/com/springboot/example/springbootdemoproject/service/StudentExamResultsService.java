package com.springboot.example.springbootdemoproject.service;

import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_CHEMISTRY;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_MATHS;
import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.EXAM_CODE_PHYSICS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.example.springbootdemoproject.model.Exam;
import com.springboot.example.springbootdemoproject.model.Student;

/**
 * 
 * The class interacts with the data set and retrieves and sends the
 * 
 * corresponding object.
 *
 *
 *
 * 
 * 
 * 
 * 
 */
@Service
public class StudentExamResultsService {
	public final static List<Student> studentsAndMarks = populateStudentAndMarks();
	public final static Map<String, Exam> examScores = populateExamScores();

	/**
	 * 
	 * Gets the student for the given topper index
	 *
	 * 
	 * 
	 * @param topperIndex
	 * 
	 * @return the student
	 * 
	 */
	public Student getStudentDetails(int topperIndex) {
		return studentsAndMarks.get(topperIndex);
	}

	/**
	 * 
	 * Gets the student details
	 *
	 * 
	 * 
	 * @return list of students
	 * 
	 */
	public List<Student> getAllStudentDetails() {
		return studentsAndMarks;
	}

	/**
	 * 
	 * Returns the highest score for given exam code
	 *
	 * 
	 * 
	 * @param examCode
	 * 
	 * @return the exam
	 * 
	 */
	public Exam getHighestScoreForExam(String examCode) {
		return examScores.get(examCode.toUpperCase());
	}

	/**
	 * 
	 * Populates the students and Marks in a list
	 * 
	 */
	private static List<Student> populateStudentAndMarks() {
		List<Student> studentsAndMarks = new ArrayList<Student>();
		studentsAndMarks.add(new Student("10", "Ashik", 495));
		studentsAndMarks.add(new Student("11", "Roshan", 490));
		studentsAndMarks.add(new Student("12", "Yazeed", 485));
		studentsAndMarks.add(new Student("13", "Zohara", 480));
		return studentsAndMarks;
	}

	/**
	 * 
	 * Populates the exam scores in a map
	 * 
	 */
	private static Map<String, Exam> populateExamScores() {
		Map<String, Exam> examScores = new HashMap<String, Exam>();
		examScores.put(EXAM_CODE_PHYSICS, new Exam("Physics", 99));
		examScores.put(EXAM_CODE_MATHS, new Exam("Mathematics", 100));
		examScores.put(EXAM_CODE_CHEMISTRY, new Exam("Chemistry", 98));
		return examScores;
	}
}
