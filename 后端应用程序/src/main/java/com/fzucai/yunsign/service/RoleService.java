package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Role;
import com.fzucai.yunsign.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {return roleRepository.findAll();}

    public String addRole(Role role){
        System.out.println(role);
        roleRepository.save(role);
        return "addsuccess";
    }

    public String deleteRole(Integer roleid){
        System.out.println(roleid);
        roleRepository.deleteById(roleid);
        return "deletesuccess";
    }

    public String updateRole(Role role){
        System.out.println(role);
        roleRepository.updateRoleById(role.roleid,role.roledetail,role.rolename);
        return "updatesuccess";
    }
}
