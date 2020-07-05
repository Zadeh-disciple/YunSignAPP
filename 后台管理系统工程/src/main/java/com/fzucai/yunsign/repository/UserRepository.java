package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Role;
import com.fzucai.yunsign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.username= :username and u.password= :password")
    User findUser(String username, String password);

    @Query("select u from User u where u.userid= :userid")
    User findByUserid(@Param("userid") Integer userid);

    @Query("select u from User u where u.username= :username")
    User findByUsername(@Param("username") String username);

    @Query("select u from User u where u.username= :username")
    User findUsername(@Param("username") String username);

    @Query("select u from User u where u.telephone= :telephone")
    User findTelephone(@Param("telephone") String telephone);

    @Query("select u from User u where u.email= :email")
    User findEmail(@Param("email") String email);

    //更新用户信息
    @Transactional
    @Modifying
    @Query("update User u set u.username = :username, u.password = :password, u.email = :email , " +
            "u.telephone =:telephone,u.role =:role where u.userid=:id")
    void updateUserById(@Param("id") Integer id, @Param("username") String username, @Param("password") String password,
                    @Param("email") String email, @Param("telephone") String telephone, @Param("role")Role role);

    @Transactional
    @Modifying
    @Query("delete from User c  where c.role.roleid = :roleid")
    void deleteByroleId(@Param("roleid")Integer roleid);


    @Transactional
    @Modifying
    @Query("delete from User c  where c.school.schoolid = :schoolid")
    void deleteByschoolId(@Param("schoolid")Integer schoolid);
}
