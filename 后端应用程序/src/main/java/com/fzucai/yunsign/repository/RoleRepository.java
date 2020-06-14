package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    @Transactional
    @Modifying
    @Query("update Role r set r.roledetail = :roledetail, r.rolename = :rolename where r.id = :id")
    void updateRoleById(@Param("id")Integer id, @Param("roledetail")String roledetail, @Param("rolename")String rolename);
}
