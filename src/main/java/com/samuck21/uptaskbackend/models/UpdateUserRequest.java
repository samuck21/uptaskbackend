package com.samuck21.uptaskbackend.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UpdateUserRequest {
    private String name;
    private String lastname;
    private String phone ;
    private MultipartFile  file;


}
