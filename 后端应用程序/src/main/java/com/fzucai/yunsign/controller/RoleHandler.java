package com.fzucai.yunsign.controller;


import com.fzucai.yunsign.entity.Role;
import com.fzucai.yunsign.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleHandler {

    @Autowired
    private RoleRepository roleRepository;
    private Role role;

    @GetMapping("/findAll")
    public List<Role> findAll() {return roleRepository.findAll();}
}
