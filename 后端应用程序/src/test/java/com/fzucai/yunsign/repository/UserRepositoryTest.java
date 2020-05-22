package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
     private  UserRepository userRepository;
    private User user;

     @Test
     void findAll(){
         System.out.println(userRepository.findAll());
         List<User> userList =userRepository.findAll();
         System.out.println(userList);
         System.out.println("测试：");
         System.out.println(userList.get(0));
         user = userList.get(1);
         System.out.println(user.username);
         for(int i=0;i<userList.size();i++){
             System.out.println("第"+ i +"个");
             System.out.println(userList.get(i).username);
             System.out.println(userList.get(i).role.rolename);
             if (("cai".equals(userList.get(i).username))&&("cai".equals(userList.get(i).password)))
             {
                 System.out.println("存在该用户");
             }
         }
     }

     @Test
     void findUser(String username,String password){
         System.out.println(userRepository.findUser("cai","cai"));
     }

}