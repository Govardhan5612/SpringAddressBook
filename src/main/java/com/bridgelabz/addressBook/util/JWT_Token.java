package com.bridgelabz.addressBook.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class JWT_Token {
    private final String SECURITY= "s";
    public String createToken(int id){
        String token= JWT.create().withClaim("id",id).sign(Algorithm.HMAC256(SECURITY));
        return token;
    }
    public int decode(String token){
        int id = JWT.require(Algorithm.HMAC256(SECURITY)).build().verify(token).getClaim("id").asInt();
        return id;
    }

    public static void main(String[] args) {
        JWT_Token j = new JWT_Token();
        System.out.println(j.createToken(502));
       // System.out.println(j.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NH0.ce_QB3Wq2mIkPqxH7h8PNY0o6l2KVymS9s42Ow2XLFo"));
    }
}
