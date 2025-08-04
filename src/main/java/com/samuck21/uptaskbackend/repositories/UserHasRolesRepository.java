package com.samuck21.uptaskbackend.repositories;

import com.samuck21.uptaskbackend.models.UserHasRoles;
import com.samuck21.uptaskbackend.models.id.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasRolesRepository extends JpaRepository<UserHasRoles, UserRoleId> {

}
