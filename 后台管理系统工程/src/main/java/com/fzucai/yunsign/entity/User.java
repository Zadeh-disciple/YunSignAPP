package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Integer userid;
//    public  String userid;
    public  String username;
    public  String password;
    public  String realname;
    public  String email;
    public  String telephone;
    @OneToOne
    @JoinColumn(name="roleid", referencedColumnName = "roleid")
    public Role role;
    @OneToOne
    @JoinColumn(name="schoolid", referencedColumnName = "schoolid")
    public School school;
}
