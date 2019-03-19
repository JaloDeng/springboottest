package com.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
