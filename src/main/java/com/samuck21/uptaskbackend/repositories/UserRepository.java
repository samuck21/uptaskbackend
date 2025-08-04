package com.samuck21.uptaskbackend.repositories;

import com.samuck21.uptaskbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
