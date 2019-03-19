package com.test.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.entity.StudentEntity;
import com.test.demo.model.Student;
import com.test.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> findAll() {
		List<StudentEntity> studentEntities = studentRepository.findAll();
		List<Student> students = new ArrayList<Student>();
		studentEntities.stream().forEach(studentEntity -> {
			Student student = new Student();
			student.setId(studentEntity.getId());
			student.setName(studentEntity.getName());
			student.setEmail(studentEntity.getEmail());
			students.add(student);
		});
		return students;
	}
	
	public Student findById(Long id) {
		Student student = new Student();
		StudentEntity studentEntity = studentRepository.getOne(id);
		student.setId(studentEntity.getId());
		student.setName(studentEntity.getName());
		student.setEmail(studentEntity.getEmail());
		return student;
	}
	
	public Student save(Student model) {
		StudentEntity entity = new StudentEntity();
		entity.setName(model.getName());
		entity.setEmail(model.getEmail());
		
		studentRepository.save(entity);
		model.setId(entity.getId());
		return model;
	}
	
	public Student update(Student model) {
		StudentEntity entity = new StudentEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setEmail(model.getEmail());
		
		studentRepository.saveAndFlush(entity);
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setEmail(entity.getEmail());
		return model;
	}
	
	public Long delete(Long id) {
		studentRepository.deleteById(id);
		return id;
	}
}
