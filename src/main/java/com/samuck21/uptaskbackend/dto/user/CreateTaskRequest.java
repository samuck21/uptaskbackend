package com.samuck21.uptaskbackend.dto.user;

import lombok.Data;

@Data
public class CreateTaskRequest {
    public String incharge;
    public String personnel;
    public String start_data;
    public String finish_data;
    public String description;
}
