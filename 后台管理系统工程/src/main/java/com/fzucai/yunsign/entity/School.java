package com.fzucai.yunsign.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer schoolid;
    public String schoolname;
    @OneToOne
    @JoinColumn(name="xueyuanid", referencedColumnName = "xueyuanid")
    public Xueyuan xueyuan;
    @OneToOne
    @JoinColumn(name="zhuanyeid", referencedColumnName = "zhuanyeid")
    public Zhuanye zhuanye;
}
