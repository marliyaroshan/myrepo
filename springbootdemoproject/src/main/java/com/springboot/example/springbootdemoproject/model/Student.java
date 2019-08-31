package com.springboot.example.springbootdemoproject.model;

public class Student {
	private String id;
	private String name;
	private int totalMarks;

	public Student(String id, String name, int totalMarks) {
		this.id = id;
		this.name = name;
		this.totalMarks = totalMarks;
	}

	/**
	 * 
	 * @return the id
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return the name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the totalMarks
	 * 
	 */
	public int getTotalMarks() {
		return totalMarks;
	}
}
