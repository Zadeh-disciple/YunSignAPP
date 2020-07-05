package com.fzucai.yunsign.service;

import com.alibaba.fastjson.JSON;
import com.fzucai.yunsign.entity.CheckResult;
import com.fzucai.yunsign.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Du Hongqing
 * @date 2020-06-02 19:43
 */
@Service("TokenService")
public class TokenService {
    /**
     * token过期时间
     */
    private static final long VALIDITY_TIME_MS = 20 * 60 * 1000;//过期时间20分钟
    /**
     * header中标识
     */
    private static final String AUTH_HEADER_NAME = "x-authorization";
    /**
     * 签名密钥
     */
    @Value("${jwt.token.secret}")
    private String secret;
    /**
     * 从用户中创建一个jwt Token
     *
     * @param user 用户
     * @return token
     */
    public String getToken(User user) {
        String jwt="";
//        token= JWT.create().withAudience(user.getUserid().toString())// 将 user id 保存到 token 里面
//                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥

        String subject = generalSubject(user);
        JwtBuilder builder = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(subject)
//                .setIssuer("lenses")// 设置签发人
//                .claim("id", user.userid)
//                .claim("realname", user.realname)
//                .claim("email", user.email)
//                .claim("role", user.role)
//                .claim("school", user.school)
                .signWith(SignatureAlgorithm.HS256, secret);
//                .compact();
        jwt = builder.compact();
        return jwt;
    }
    // 解析token
    public Claims parseJWT(String jwt){//throws Exception 抛出异常如token过期、token无效等
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
    }

//    验证token是否过期，签名是否正确
    public CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true); // 验证成功
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(1007); // Token过期
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(1008); // 验证不通过
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(1008); // 验证不通过
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 生成subject信息
     *
     * @param user
     * @return
     */
    public String generalSubject(User user) {
        return JSON.toJSONString(user);
    }
}
