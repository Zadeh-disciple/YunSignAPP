package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.entity.UserLogin;
import com.fzucai.yunsign.repository.UserLoginRepository;
import com.fzucai.yunsign.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;
    private UserLoginRepository userLoginRepository;
    private boolean t=false;
//    加入构造函数
    public UserHandler(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/findAllLogin")
    public List<UserLogin> findAllLogin(){
        return userLoginRepository.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin){
//    public String login(@PathVariable String username){
        List<User> userList =userRepository.findAll();
        for(int i=0;i<userList.size();i++){
            System.out.println("第"+ i +"个");
            System.out.println(userList.get(i));
            if ((userLogin.username.equals(userList.get(i).username))&&(userLogin.password.equals(userList.get(i).password)))
            {
                System.out.println("存在该用户");
                return "success";
            }
        }
        return "error";
    }
}
