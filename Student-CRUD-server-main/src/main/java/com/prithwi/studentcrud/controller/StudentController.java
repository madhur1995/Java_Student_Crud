package com.prithwi.studentcrud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prithwi.studentcrud.entity.Student;
import com.prithwi.studentcrud.exception.StudentCrudAppException;
import com.prithwi.studentcrud.exception.service.StudentCrudServiceException;
import com.prithwi.studentcrud.service.IStudentCrudService;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IStudentCrudService studentService;

	@PostMapping(value = "/add")
	public ResponseEntity<String> registerStudent(@RequestBody Student student) throws StudentCrudAppException {

		log.info(student.toString());
		
		return new ResponseEntity<String>(studentService.addStudent(student), HttpStatus.ACCEPTED);

	}

	@GetMapping(value = "/getAllStu")
	public ResponseEntity<List<Student>> displayAllStudents() {
		List<Student> list;
		try {
			list = studentService.getAllStudent();
			return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		} catch (StudentCrudServiceException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getStudent/{studentId}")
	public ResponseEntity<Student> displayStudentById(@PathVariable("studentId") int studentId)
			throws StudentCrudAppException {

		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);

	}

	@GetMapping(value = "/updateStudent/{studentId}")
	public ResponseEntity<?> updateStudentDetailById(@PathVariable("studentId") int studentId,
			@RequestParam int studentMarks) throws StudentCrudAppException {

		return new ResponseEntity<>(studentService.updateStudentMarks(studentId, studentMarks), HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = "/deleteStudent")
	public ResponseEntity<?> deleteStudentById(@RequestParam("studentId") int studentId)
			throws StudentCrudAppException {

		return new ResponseEntity<>(studentService.deleteStudentByID(studentId), HttpStatus.ACCEPTED);

	}

}
