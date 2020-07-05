package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    private Role role;

    @Test
    void findAll(){
        System.out.println(roleRepository.findAll());
    }

}
