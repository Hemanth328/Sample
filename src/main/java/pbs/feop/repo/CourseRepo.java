package pbs.feop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pbs.feop.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
