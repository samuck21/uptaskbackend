package com.samuck21.uptaskbackend.dto.user;

import lombok.Data;

@Data
public class LoginResponse {
    private  String token;
    private CreateUserResponse user;

}
