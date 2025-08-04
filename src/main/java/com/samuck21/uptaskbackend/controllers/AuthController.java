package com.samuck21.uptaskbackend.controllers;

import com.samuck21.uptaskbackend.dto.user.CreateUserRequest;
import com.samuck21.uptaskbackend.models.User;
import com.samuck21.uptaskbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {

        try {
            User user = userService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message", e.getMessage(),
                            "statusCode",HttpStatus.BAD_REQUEST.value()

                    )
            );
        }


    }
}
