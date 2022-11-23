package com.pinterngoding.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_records")
public class CourseRecord {
    @EmbeddedId
    private CourseRecordKey courseRecordKey;

    @Column(name = "score")
    private Integer score;

    @Column(name = "approved", nullable = false)
    private Boolean approved = false;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseRecord() {
        this.courseRecordKey = new CourseRecordKey();
    }

    public CourseRecordKey getCourseRecordKey() {
        return courseRecordKey;
    }

    public void setCourseRecordKey(CourseRecordKey courseRecordKey) {
        this.courseRecordKey = courseRecordKey;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}