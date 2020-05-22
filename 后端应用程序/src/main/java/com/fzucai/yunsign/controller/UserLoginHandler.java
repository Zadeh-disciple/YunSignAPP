package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.entity.UserLogin;
import com.fzucai.yunsign.repository.UserLoginRepository;
import com.fzucai.yunsign.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user3")
public class UserLoginHandler {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @GetMapping("/findAllLogin")
    public List<UserLogin> findAllLogin(){
        return userLoginRepository.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin){
//    public String login(@PathVariable String username){
        List<UserLogin> userLoginList =userLoginRepository.findAll();
        for(int i=0;i<userLoginList.size();i++){
            System.out.println("第"+ i +"个");
            System.out.println(userLoginList.get(i));
            if ((userLogin.username.equals(userLoginList.get(i).username))
                    &&(userLogin.password.equals(userLoginList.get(i).password)))
            {
                System.out.println("存在该用户");
                return "success";
            }
        }
        return "error";
    }
}
