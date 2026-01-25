package com.cpaums.config;

import com.cpaums.model.Permission;
import com.cpaums.model.Role;
import com.cpaums.repository.PermissionRepository;
import com.cpaums.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @PostConstruct
    public void init() {
        createPermissionIfNotFound("CREATE_VERSION");
        createPermissionIfNotFound("VIEW_STATS");
        createPermissionIfNotFound("MANAGE_USERS");
        
        createRoleIfNotFound("ROLE_USER", Arrays.asList("CREATE_VERSION"));
        createRoleIfNotFound("ROLE_MANAGER", Arrays.asList("CREATE_VERSION", "VIEW_STATS"));
        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList("CREATE_VERSION", "VIEW_STATS", "MANAGE_USERS"));
    }
    
    private void createPermissionIfNotFound(String name) {
        if (permissionRepository.findByName(name).isEmpty()) {
            Permission permission = new Permission();
            permission.setName(name);
            permissionRepository.save(permission);
        }
    }
    
    private void createRoleIfNotFound(String name, Iterable<String> permissionNames) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role role = new Role();
            role.setName(name);
            role.setPermissions(new HashSet<>());
            
            for (String permName : permissionNames) {
                permissionRepository.findByName(permName).ifPresent(role.getPermissions()::add);
            }
            
            roleRepository.save(role);
        }
    }
}