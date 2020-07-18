package com.jonathanlee.springsecurity_pro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecurityProApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 10; i++) {
            System.out.println(encoder.encode("1234"));
        }
    }

}