package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Course_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer cuid;
    public Integer totalexp;
    @OneToOne
    @JoinColumn(name="courseid", referencedColumnName = "courseid")
    public Course course;
    @OneToOne
    @JoinColumn(name="userid", referencedColumnName = "userid")
    public User user;
}
