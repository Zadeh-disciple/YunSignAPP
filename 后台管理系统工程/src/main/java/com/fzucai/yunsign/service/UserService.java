package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.entity.UserLogin;
import com.fzucai.yunsign.repository.Course_userRepository;
import com.fzucai.yunsign.repository.SignRepository;
import com.fzucai.yunsign.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.fzucai.yunsign.mapper.UserMapper;


@Service("UserService")
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Course_userRepository course_userRepository;
    @Autowired
    SignRepository signRepository;

    public User findUserByUsername(User user){
        return userRepository.findByUsername(user.username);
    }

    public User findUserLogin(User user){
        if(userRepository.findUsername(user.username) != null){
            return userRepository.findUsername(user.username);
        }
        else if(userRepository.findTelephone(user.telephone) != null){
            return userRepository.findTelephone(user.telephone);
        }
        else if(userRepository.findEmail(user.email) != null){
            return userRepository.findEmail(user.email);
        }
        else{
            return null;
        }
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
        course_userRepository.deleteByuserId(userid);
        System.out.println("111");
        signRepository.deleteByuserId(userid);
        System.out.println("222");
        userRepository.deleteById(userid);
        System.out.println("333");
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

