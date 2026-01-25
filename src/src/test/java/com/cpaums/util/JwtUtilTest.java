package com.cpaums.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
    
    private JwtUtil jwtUtil;
    
    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "testSecretKeytestSecretKeytestSecretKey!!");
        ReflectionTestUtils.setField(jwtUtil, "expiration", 3600000L);
    }
    
    @Test
    void generateToken_Success() {
        String token = jwtUtil.generateToken("testuser");
        
        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();
    }
    
    @Test
    void extractUsername_Success() {
        String token = jwtUtil.generateToken("testuser");
        String username = jwtUtil.extractUsername(token);
        
        assertThat(username).isEqualTo("testuser");
    }
    
    @Test
    void isTokenValid_ValidToken() {
        String token = jwtUtil.generateToken("testuser");
        boolean isValid = jwtUtil.isTokenValid(token);
        
        assertThat(isValid).isTrue();
    }
    
    @Test
    void isTokenValid_InvalidToken() {
        boolean isValid = jwtUtil.isTokenValid("invalid.token.here");
        
        assertThat(isValid).isFalse();
    }
}