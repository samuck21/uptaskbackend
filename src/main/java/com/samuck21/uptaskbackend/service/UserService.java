package com.samuck21.uptaskbackend.service;

import com.samuck21.uptaskbackend.dto.role.RoleDTO;
import com.samuck21.uptaskbackend.dto.user.CreateUserRequest;
import com.samuck21.uptaskbackend.dto.user.CreateUserResponse;
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

import java.util.List;

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
    public CreateUserResponse create(CreateUserRequest request){
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

        CreateUserResponse response = new CreateUserResponse();
        response.setId(saveUser.getId());
        response.setName(saveUser.getName());
        response.setLastname(saveUser.getLastname());
        response.setImage(saveUser.getImage());
        response.setPhone(saveUser.getPhone());
        response.setEmail(saveUser.getEmail());

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(saveUser.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(),role.getName(),role.getImage(),role.getRoute()))
                .toList();
         response.setRoles(roleDTOS);

       return  response;
    }
}
