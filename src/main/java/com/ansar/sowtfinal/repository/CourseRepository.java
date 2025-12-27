package com.ansar.sowtfinal.repository;

import com.ansar.sowtfinal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
