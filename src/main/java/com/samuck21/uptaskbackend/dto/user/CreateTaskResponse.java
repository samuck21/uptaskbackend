package com.samuck21.uptaskbackend.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.samuck21.uptaskbackend.dto.role.RoleDTO;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class CreateTaskResponse {
    public Long id;
    public String incharge;
    public String personnel;
    public String start_data;
    public String finish_data;
    public String description;
}
