package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer signid;
    public Integer exp;
    public Integer issigned;
    public Date signtime;
    @OneToOne
    @JoinColumn(name="courseid", referencedColumnName = "courseid")
    public Course course;

    @OneToOne
    @JoinColumn(name="userid", referencedColumnName = "userid")
    public User user;
}
