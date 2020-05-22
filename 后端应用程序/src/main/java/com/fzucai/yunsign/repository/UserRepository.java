package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.username= username and u.password= password")
    User findUser(String username, String password);

}
