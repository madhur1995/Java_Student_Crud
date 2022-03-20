package com.prithwi.studentcrud.service;

import java.util.List;

import com.prithwi.studentcrud.entity.Student;
import com.prithwi.studentcrud.exception.service.NoResultFounndException;
import com.prithwi.studentcrud.exception.service.StudentCrudServiceException;

public interface IStudentCrudService {

	String addStudent(Student student) throws  StudentCrudServiceException;
	
	public List<Student> getAllStudent() throws StudentCrudServiceException;
	
	public Student getStudentById(int studentId) throws StudentCrudServiceException;
	
	public String updateStudentMarks(int id, int marks) throws StudentCrudServiceException;
	
	public String deleteStudentByID(int id) throws StudentCrudServiceException;

}
