package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.CourseCreateRequest;
import com.ansar.sowtfinal.entity.Course;
import com.ansar.sowtfinal.entity.TeacherProfile;
import com.ansar.sowtfinal.repository.CourseRepository;
import com.ansar.sowtfinal.repository.TeacherProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherProfileRepository teacherProfileRepository;
    private final UserLookupService userLookupService;

    public CourseService(CourseRepository courseRepository,
                         TeacherProfileRepository teacherProfileRepository,
                         UserLookupService userLookupService) {
        this.courseRepository = courseRepository;
        this.teacherProfileRepository = teacherProfileRepository;
        this.userLookupService = userLookupService;
    }

    public Course createCourse(Authentication auth, CourseCreateRequest req) {
        var user = userLookupService.currentUser(auth);
        TeacherProfile teacher = teacherProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher profile not found"));

        Course c = Course.builder()
                .code(req.code())
                .title(req.title())
                .description(req.description())
                .credits(req.credits())
                .teacher(teacher)
                .build();

        return courseRepository.save(c);
    }
}
