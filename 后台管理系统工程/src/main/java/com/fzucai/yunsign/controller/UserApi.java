package com.fzucai.yunsign.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzucai.yunsign.annotation.UserLoginToken;
import com.fzucai.yunsign.entity.User;
import com.fzucai.yunsign.service.TokenService;
import com.fzucai.yunsign.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Du Hongqing
 * @date 2020-06-02 19:43
 */

@RestController
@RequestMapping("api")
@Slf4j
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findUserLogin(user);
        Date date = new Date();
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            jsonObject.put("status", "failed");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                jsonObject.put("status", "failed");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("message","登录成功");
                jsonObject.put("status", "success");
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }
    //解析token，验证token是否过期，签名是否正确，返回验证结果及user对象
    @GetMapping("parsejwt")
    public Object parsejwt(HttpServletRequest request) throws Exception{
//        String Token = request.getParameter("token");
        String Token = request.getHeader("Authorization");
        JSONObject jsonObject =new JSONObject();
//        if (Token == null || Token.length() <= 0){
//            jsonObject.put("message","token_is_null");
//            return jsonObject;
//        }
//        if(user == null){
//            jsonObject1.put("message","token_is_invalid");
//        }
//        jsonObject1.put("message","token_is_valid");
        jsonObject.put("message",tokenService.validateJWT(Token));
        if (tokenService.validateJWT(Token).isSuccess()){
            Claims claims = tokenService.parseJWT(Token);
            String subject = claims.getSubject();
            JSONObject jsonObject2 = JSON.parseObject(subject);
            User user = JSON.toJavaObject(jsonObject2, User.class);
            jsonObject.put("user",user);
        }
        return jsonObject;
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        log.info("haha");
        return "你已通过验证";
    }
}
