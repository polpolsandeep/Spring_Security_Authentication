package com.sandeep.base.RoleBaseAuthorization.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.base.RoleBaseAuthorization.Entity.Student;
import com.sandeep.base.RoleBaseAuthorization.Repository.StudentsRepository;

@RestController
public class StudentsController {
	
	@Autowired
	StudentsRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	@PutMapping("/update{id}")
	public Student update(@PathVariable int id,@RequestBody Student student) {
		Student existing = studentRepository.findById(id).get();
		existing.setName(student.getName());
		existing.setMarks(student.getMarks());
		studentRepository.save(existing);
		return existing;
	}
	@DeleteMapping("/delete{id}")
	public Map<String,Boolean> delete(@PathVariable int id){
	     Student existing=studentRepository.findById(id).get();
	     studentRepository.delete(existing);
	     Map<String,Boolean> response=new HashMap<String,Boolean>();
	     response.put("Deleted",Boolean.TRUE);
	     return response;
	}

}
