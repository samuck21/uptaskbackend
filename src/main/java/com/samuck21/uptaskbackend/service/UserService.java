package com.samuck21.uptaskbackend.service;

import com.samuck21.uptaskbackend.dto.user.CreateUserRequest;
import com.samuck21.uptaskbackend.models.Role;
import com.samuck21.uptaskbackend.models.User;
import com.samuck21.uptaskbackend.models.UserHasRoles;
import com.samuck21.uptaskbackend.repositories.RoleRepository;
import com.samuck21.uptaskbackend.repositories.UserHasRolesRepository;
import com.samuck21.uptaskbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserHasRolesRepository userHasRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public User create(CreateUserRequest request){
     if(userRepository.existsByEmail(request.email)){
         throw new RuntimeException("El correo ya esta registrado");
     }
     User user = new User();
        user.setName(request.name);
        user.setLastname(request.lastname);
        user.setPhone(request.phone);
        user.setEmail(request.email);
        user.setPassword(request.password);
        String encrytedPassword = passwordEncoder.encode(request.password);
        user.setPassword(encrytedPassword);

        User saveUser = userRepository.save(user);
        Role clientRole = roleRepository.findById("CLIENT").orElseThrow( ()-> new RuntimeException("El rol de cliente no Existe"));
        UserHasRoles userHasRoles = new UserHasRoles(saveUser,clientRole);
        userHasRolesRepository.save(userHasRoles);
       return  saveUser;
    }
}
