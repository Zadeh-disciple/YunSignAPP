package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.entity.UserLogin;
import com.fzucai.yunsign.repository.UserRepository;
import com.fzucai.yunsign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;
//    private boolean t = false;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }


    //新建用户
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //删除用户
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer userid){
        return userService.deleteUser(userid);
    }

    //修改用户
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin){
        return userService.login(userLogin);
    }
//    @PostMapping("/login")
//    public String login(@RequestBody User user){
//        List<User> userList =userRepository.findAll();
//
//        for(int i=0;i<userList.size();i++){
//            System.out.println("第"+ i +"个");
//            System.out.println(userList.get(i));
//            if ((user.username.equals(userList.get(i).username))&&(user.password.equals(userList.get(i).password)))
//            {
//                System.out.println("存在该用户");
//                return "success";
//            }
//        }
//        return "error";
//    }
}
