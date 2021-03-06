package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  String username;
    public  String password;

}
