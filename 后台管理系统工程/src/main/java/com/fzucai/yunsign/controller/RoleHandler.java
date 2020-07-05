package com.fzucai.yunsign.controller;


import com.fzucai.yunsign.entity.Role;
import com.fzucai.yunsign.repository.RoleRepository;
import com.fzucai.yunsign.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleHandler {

    @Autowired
    private RoleService roleService;
    private Role role;

    @GetMapping("/findAll")
    public List<Role> findAll() {return roleService.findAll();}

    //新建角色
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    //删除角色
    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam Integer roleid){
        return roleService.deleteRole(roleid);
    }

    //修改角色
    //修改用户
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public String updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
}
