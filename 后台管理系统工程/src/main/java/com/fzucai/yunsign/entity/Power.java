package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Power {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String powerid;
    public String powername;
    public String powerlujin;
    public String powerlevel;
}
