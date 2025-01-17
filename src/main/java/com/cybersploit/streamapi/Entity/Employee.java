package com.cybersploit.streamapi.Entity;

import jakarta.persistence.*;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    private String department;

    @Column(nullable = false, unique = true)
    private Integer rollNumber;

    private String subject;

    @Column(nullable = false)
    private Integer classesAttended ;

    @Column(nullable = false)
    private Double attendance ;

    public Employee() {
    }

    public Employee(Integer id, String email, String fname, String lname, String department, Integer rollNumber, String subject, Integer classesAttended, Double attendance) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.department = department;
        this.rollNumber = rollNumber;
        this.subject = subject;
        this.classesAttended = classesAttended;
        this.attendance = attendance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getClassesAttended() {
        return classesAttended;
    }

    public void setClassesAttended(Integer classesAttended) {
        this.classesAttended = classesAttended;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }



}

