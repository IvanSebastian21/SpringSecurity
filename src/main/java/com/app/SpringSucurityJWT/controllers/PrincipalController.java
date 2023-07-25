package com.app.SpringSucurityJWT.controllers;

import com.app.SpringSucurityJWT.controllers.request.CreatedUserDTO;
import com.app.SpringSucurityJWT.models.EnumRole;
import com.app.SpringSucurityJWT.models.RoleEntity;
import com.app.SpringSucurityJWT.models.UserEntity;
import com.app.SpringSucurityJWT.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Users")
public class PrincipalController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello word Not Secure";
    }

    @GetMapping("/helloSecure")
    public String helloSecure() {
        return "Hello word Secure";
    }

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreatedUserDTO createdUserDTO) {

        Set<RoleEntity> roles = createdUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(EnumRole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createdUserDTO.getUsername())
                .password(passwordEncoder.encode(createdUserDTO.getPassword()))
                .email(createdUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteById(@RequestParam String id) {

            userRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.ok("Se eliminado correctamente el usuario con ID: ".concat(id));

    }

}

