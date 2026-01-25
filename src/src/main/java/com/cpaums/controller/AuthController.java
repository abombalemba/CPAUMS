package com.cpaums.controller;

import com.cpaums.dto.AuthResponse;
import com.cpaums.dto.LoginRequest;
import com.cpaums.dto.RegisterRequest;
import com.cpaums.service.AuthService;
import com.cpaums.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info("Registering new user: {}", request.getUsername());
        AuthResponse response = authService.register(request);
        log.info("User registered successfully: {}", request.getUsername());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        log.info("Login attempt for user: {}", loginRequest.getUsername());
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            
            Cookie jwtCookie = new Cookie("auth-token", jwt);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60);
            jwtCookie.setHttpOnly(true);
            
            response.addCookie(jwtCookie);
            
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("username", userDetails.getUsername());
            responseMap.put("authorities", userDetails.getAuthorities());
            responseMap.put("token", jwt);
            
            log.info("User logged in successfully: {}", loginRequest.getUsername());
            return ResponseEntity.ok(responseMap);
            
        } catch (Exception e) {
            log.error("Login failed for user: {}. Reason: {}", loginRequest.getUsername(), e.getMessage());
            throw e;
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info("User logging out: {}", auth.getName());
        }
        
        Cookie cleanCookie = new Cookie("auth-token", "");
        cleanCookie.setPath("/");
        cleanCookie.setMaxAge(0);
        cleanCookie.setHttpOnly(true);
        
        response.addCookie(cleanCookie);
        log.info("Logout successful");
        return ResponseEntity.ok("Logged out successfully");
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Unauthenticated access to /me endpoint");
            return ResponseEntity.status(401).body("Not authenticated");
        }
        
        log.debug("User info requested for: {}", authentication.getName());
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("authorities", authentication.getAuthorities());
        
        return ResponseEntity.ok(userInfo);
    }
}