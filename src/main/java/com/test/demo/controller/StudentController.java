package com.test.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.demo.model.Student;
import com.test.demo.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Student> findAll(HttpServletRequest request, HttpServletResponse response) {
		return studentService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Cacheable(value = "student", key = "#id")
	public @ResponseBody Student findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		return studentService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CachePut(value = "student", key = "#student.id")
	public @ResponseBody Student save(@RequestBody Student student, HttpServletRequest request, HttpServletResponse response) {
		return studentService.save(student);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@CachePut(value = "student", key = "#student.id")
	public @ResponseBody Student update(@RequestBody Student student, HttpServletRequest request, HttpServletResponse response) {
		return studentService.update(student);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CacheEvict(value = "student", key = "#id")
	public @ResponseBody Long delete(@RequestBody Long id, HttpServletRequest request, HttpServletResponse response) {
		return studentService.delete(id);
	}
}
