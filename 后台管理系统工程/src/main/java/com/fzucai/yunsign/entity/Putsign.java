package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Putsign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Integer putsignid;
    public  Double longitude;
    public  Double latitude;
    public  String gesture;
    @OneToOne
    @JoinColumn(name="courseid", referencedColumnName = "courseid")
    public Course course;
}
