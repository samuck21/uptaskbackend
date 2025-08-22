package com.samuck21.uptaskbackend.dto.user;

import lombok.Data;

@Data
public class UpdateTaskRequest {
   // public Long id;
   public String incharge;
    public String personnel;
    public String start_data;
    public String finish_data;
    public String description;
}
