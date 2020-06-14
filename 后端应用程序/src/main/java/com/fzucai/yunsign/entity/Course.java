package com.fzucai.yunsign.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "course")
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer courseid;
    public String coursename;
    public String courseinfo;
    public String courselocation;
    public String coursetime;
    @OneToOne
    @JoinColumn(name="schoolid", referencedColumnName = "schoolid")
    public School school;
}
