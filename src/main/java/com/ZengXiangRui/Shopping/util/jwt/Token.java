package com.LiZhuang.Shopping.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class Token {
    private final Logger logger = LoggerFactory.getLogger(Token.class);
    private static final String SECRET_KEY = "shopping-vx";

    public String getToken(String username) {
        String token = null;
        try {
            token = JWT.create().withAudience(username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return token;
    }
}
