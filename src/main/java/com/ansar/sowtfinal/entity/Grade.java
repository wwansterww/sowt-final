package com.ansar.sowtfinal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "grades")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Grade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "value_int", nullable = false)
    private Integer value;

    @Column(length = 500)
    private String comment;

    @Column(name = "graded_at", nullable = false)
    private Instant gradedAt;
}



