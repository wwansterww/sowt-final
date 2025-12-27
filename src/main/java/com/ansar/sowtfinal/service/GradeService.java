package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.GradeCreateRequest;
import com.ansar.sowtfinal.entity.Course;
import com.ansar.sowtfinal.entity.Grade;
import com.ansar.sowtfinal.entity.StudentProfile;
import com.ansar.sowtfinal.repository.CourseRepository;
import com.ansar.sowtfinal.repository.GradeRepository;
import com.ansar.sowtfinal.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentProfileRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeService(GradeRepository gradeRepository,
                        StudentProfileRepository studentRepository,
                        CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Grade addGrade(GradeCreateRequest req) {
        StudentProfile student = studentRepository.findById(req.studentProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(req.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Grade g = Grade.builder()
                .student(student)
                .course(course)
                .value(req.value())
                .comment(req.comment())
                .gradedAt(Instant.now())
                .build();

        return gradeRepository.save(g);
    }

    public List<Grade> getStudentGrades(Long studentProfileId) {
        return gradeRepository.findByStudentId(studentProfileId);
    }
}
