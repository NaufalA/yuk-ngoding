package com.pinterngoding.entity;

import com.pinterngoding.shared.classes.BaseEntity;
import com.pinterngoding.shared.constants.PricingType;
import com.pinterngoding.shared.constants.CourseCategory;
import jakarta.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_type", nullable = false)
    private PricingType pricingType = PricingType.FREE;

    @Column(name = "price", nullable = false)
    private Integer price = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_category", nullable = false)
    private CourseCategory courseCategory = CourseCategory.GENERAL;

    @Column(name = "min_score", nullable = false)
    private Integer minScore;

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
        return endDate;
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

    public CourseCategory getTrainingCategory() {
        return courseCategory;
    }

    public void setTrainingCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
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