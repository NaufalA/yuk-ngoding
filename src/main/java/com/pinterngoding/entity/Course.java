package com.pinterngoding.entity;

import com.pinterngoding.shared.classes.BaseEntity;
import com.pinterngoding.shared.constants.PricingType;
import com.pinterngoding.shared.constants.CourseCategory;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate = new Date(System.currentTimeMillis());

    @Column(name = "duration", nullable = false)
    private Integer duration = 30;

    @Transient
    private Date endDate = new Date(startDate.getTime() + duration * TimeUnit.DAYS.toMillis(duration));

    @Temporal(TemporalType.TIME)
    @Column(name = "schedule_start", nullable = false)
    private Time scheduleStart = Time.valueOf("08:00:00");

    @Temporal(TemporalType.TIME)
    @Column(name = "schedule_end", nullable = false)
    private Time scheduleEnd = Time.valueOf("17:00:00");

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_type", nullable = false)
    private PricingType pricingType = PricingType.FREE;

    @Column(name = "price", nullable = false)
    private Integer price = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_category", nullable = false)
    private CourseCategory courseCategory = CourseCategory.GENERAL;

    @Column(name = "min_score", nullable = false)
    private Integer minScore = 0;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName = "";

    @OneToMany(mappedBy = "course")
    private List<CourseRecord> courseRecords = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        endDate = new Date(startDate.getTime() + duration * TimeUnit.DAYS.toMillis(duration));
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
        endDate = new Date(startDate.getTime() + duration * TimeUnit.DAYS.toMillis(duration));
    }

    public Date getEndDate() {
        endDate = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(duration));
        return endDate;
    }

    public Time getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(Time scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public Time getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(Time scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public void setPricingType(PricingType pricingType) {
        this.pricingType = pricingType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<CourseRecord> getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(List<CourseRecord> courseRecords) {
        this.courseRecords = courseRecords;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", pricingType=" + pricingType +
                ", trainingCategory=" + courseCategory +
                ", minScore=" + minScore +
                '}';
    }
}