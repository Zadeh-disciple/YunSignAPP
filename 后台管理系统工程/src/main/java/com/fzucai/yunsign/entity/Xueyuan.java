package com.fzucai.yunsign.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Xueyuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer xueyuanid;
    public String xueyuanname;
}



