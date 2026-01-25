package com.cpaums.controller;

import com.cpaums.dto.LoginRequest;
import com.cpaums.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        
        ResponseCookie jwtCookie = ResponseCookie.from("auth-token", jwt)
            .path("/")
            .maxAge(24 * 60 * 60)
            .httpOnly(true)
            .build();
        
        response.addHeader("Set-Cookie", jwtCookie.toString());
        
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("username", userDetails.getUsername());
        responseMap.put("authorities", userDetails.getAuthorities());
        responseMap.put("token", jwt);
        
        return ResponseEntity.ok(responseMap);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie cleanCookie = ResponseCookie.from("auth-token", "")
            .path("/")
            .maxAge(0)
            .httpOnly(true)
            .build();
        
        response.addHeader("Set-Cookie", cleanCookie.toString());
        return ResponseEntity.ok("Logged out successfully");
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("authorities", authentication.getAuthorities());
        
        return ResponseEntity.ok(userInfo);
    }
}