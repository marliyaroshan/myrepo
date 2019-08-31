package com.springboot.example.springbootdemoproject.model;

public class Exam {
	private String subject;
	private int score;

	/**
	 * 
	 * @return the score
	 * 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @param score
	 *
	 * 
	 *            the score to set
	 * 
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 
	 * @return the subject
	 * 
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 
	 * @param subject
	 *
	 * 
	 *            the subject to set
	 * 
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Exam(String subject, int score) {
		this.subject = subject;
		this.score = score;
	}
}
