package com.fzucai.yunsign.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Zhuanye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer zhuanyeid;
    public String zhuanyename;
}



