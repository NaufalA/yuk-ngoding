package com.pinterngoding.entity;

import com.pinterngoding.shared.classes.BaseEntity;
import com.pinterngoding.shared.constants.Education;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone", nullable = false, length = 15, unique = true)
    private String phone;
    @Column(name = "identity_number", nullable = false, length = 15, unique = true)
    private String identityNumber;
    @Column(name = "education", nullable = false)
    @Enumerated(EnumType.STRING)
    private Education education;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student")
    private List<CourseRecord> courseRecords = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CourseRecord> getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(List<CourseRecord> courseRecords) {
        this.courseRecords = courseRecords;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", education=" + education +
                '}';
    }
}