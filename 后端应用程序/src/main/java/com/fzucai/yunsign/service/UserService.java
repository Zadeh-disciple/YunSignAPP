package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.entity.UserLogin;
//import com.fzucai.yunsign.mapper.UserMapper;
import com.fzucai.yunsign.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service("UserService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserByUsername(User user){
        return userRepository.findByUsername(user.username);
    }

//    public User findUserById(Integer userId) {
//        return userMapper.findUserById(userId.toString());
//    }
    public User findUserById(Integer userId) {
    return userRepository.findByUserid(userId);
}

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        System.out.println(user);
        userRepository.save(user);
        return "addsuccess";
    }

    public String deleteUser(Integer userid) {
        System.out.println(userid);
        userRepository.deleteById(userid);
        return "deletesuccess";
    }

    public String updateUser(User user) {
        System.out.println(user);
        userRepository.updateUserById(user.userid, user.username, user.password, user.email, user.telephone, user.role);
        return "updatesuccess";
    }

    public String login(UserLogin userLogin) {
//    public String login(@PathVariable String username){
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("第" + i + "个");
            System.out.println(userList.get(i));
            if ((userLogin.username.equals(userList.get(i).username))
                    && (userLogin.password.equals(userList.get(i).password))) {
                System.out.println("存在该用户");
                return "success";
            }
        }
        return "error";
    }
}

