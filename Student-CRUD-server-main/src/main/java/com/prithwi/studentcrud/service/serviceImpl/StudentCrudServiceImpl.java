package com.prithwi.studentcrud.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prithwi.studentcrud.entity.Student;
import com.prithwi.studentcrud.exception.service.InvalidNameException;
import com.prithwi.studentcrud.exception.service.InvalidStudentMarksException;
import com.prithwi.studentcrud.exception.service.NoResultFounndException;
import com.prithwi.studentcrud.exception.service.StudentCrudServiceException;
import com.prithwi.studentcrud.repository.StudentRepository;
import com.prithwi.studentcrud.service.IStudentCrudService;

@Service
@Transactional
public class StudentCrudServiceImpl implements IStudentCrudService {

	@Autowired
	StudentRepository studentRepo;

	@Override
	public String addStudent(Student student) throws StudentCrudServiceException {
		if (student.getPercentage() < 35) {
			throw new InvalidStudentMarksException("Marks should be greater than 35");
		} else if (student.getName().length() < 3) {
			throw new InvalidNameException("Name length should be greater than 3");
		}
		studentRepo.save(student);
		return "Student Added";
	}

	@Override
	public List<Student> getAllStudent() throws StudentCrudServiceException {
		List<Student> list = studentRepo.findAll();
		if (list.isEmpty()) {
			throw new NoResultFounndException("No result found");
		}
		return list;
	}

	@Override
	public Student getStudentById(int studentId) throws StudentCrudServiceException {
		Student student = studentRepo.getOne(studentId);
		if (student == null) {
			throw new NoResultFounndException("No student found");
		}
		return student;
	}

	@Override
	public String updateStudentMarks(int id, int marks) throws StudentCrudServiceException {
		List<Student> studentDetailList = studentRepo.findAll();

		if (studentDetailList.isEmpty()) {
			throw new NoResultFounndException("No result found");
		}

		boolean result = studentDetailList.stream().anyMatch(e -> e.getId() == id);

		if (result) {

			Optional<Student> student = studentRepo.findById(id);
			Student newStudentObj = new Student();
			newStudentObj.setId(student.get().getId());
			newStudentObj.setName(student.get().getName());
			newStudentObj.setPercentage(marks);

			studentRepo.save(newStudentObj);
		} else {
			throw new NoResultFounndException("No result found");
		}

		return "Student updated";
	}

	@Override
	public String deleteStudentByID(int id) throws StudentCrudServiceException {
		studentRepo.findById(id).orElseThrow(() -> new NoResultFounndException("No record found"));

		studentRepo.deleteById(id);
		return "Student deleted";

	}

}
