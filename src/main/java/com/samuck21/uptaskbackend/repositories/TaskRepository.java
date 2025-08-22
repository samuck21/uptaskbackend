package com.samuck21.uptaskbackend.repositories;

import com.samuck21.uptaskbackend.models.Task;
import com.samuck21.uptaskbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    //boolean existsByEmail(String email);
    //Optional<User> findByEmail(String email);
}
