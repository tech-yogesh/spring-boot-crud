package techyogesh.com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techyogesh.com.entity.Student;
import techyogesh.com.exception.ResourceNotFoundException;
import techyogesh.com.repository.StudentRepository;

@RestController
@RequestMapping("api/v1")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Student student = studentRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Student not found for this id :: " + id));
			return ResponseEntity.ok().body(student);
	}
	
	@PostMapping("/student")
	public void createStudent(@RequestBody Student student) {
		studentRepository.save(student);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id , @RequestBody Student studentDetail) throws ResourceNotFoundException {
		Student student = studentRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Student not found for this id :: " + id));
		
		student.setName(studentDetail.getName());
		student.setAddress(studentDetail.getAddress());
		student.setEmail(studentDetail.getEmail());
		final Student updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	@DeleteMapping("/student/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Student student = studentRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Student not found for this id :: " + id));
		
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}

}
