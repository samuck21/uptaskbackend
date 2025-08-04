package com.samuck21.uptaskbackend.repositories;

import com.samuck21.uptaskbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    boolean existsByName(String name);

}