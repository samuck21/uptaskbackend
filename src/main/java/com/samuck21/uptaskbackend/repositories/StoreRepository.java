package com.samuck21.uptaskbackend.repositories;

import com.samuck21.uptaskbackend.models.Store;
import com.samuck21.uptaskbackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
    //boolean existsByEmail(String email);
    //Optional<User> findByEmail(String email);
}
