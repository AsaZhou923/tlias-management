package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 测试生成JWT
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                .addClaims(dataMap) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 1000))// 设置过期时间
                .compact();// 生成JWT
        System.out.println(jwt);
    }

    // 测试解析JWT
    @Test
    public void testParseJwt(){
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0MDQ4NTY4Nn0.g4KnaL65Hg6SEac8VCBqb65bRxfpyT71NAZcsd8dstg";
        Claims claims = Jwts.parser()
                .setSigningKey("c2VjcmV0")
                .parseClaimsJws(s)
                .getBody();
        System.out.println(claims);
    }
}
