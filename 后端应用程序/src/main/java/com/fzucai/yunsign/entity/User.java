package com.fzucai.yunsign.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  String userid;
    public  String  username;
    public  String password;
    public  String email;
    public  String telephone;
    @OneToOne
    @JoinColumn(name="roleid", referencedColumnName = "roleid")
    public Role role;
}
