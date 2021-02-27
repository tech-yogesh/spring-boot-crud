package techyogesh.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import techyogesh.com.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
