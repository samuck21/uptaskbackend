package com.samuck21.uptaskbackend.service;

import com.samuck21.uptaskbackend.dto.role.RoleDTO;
import com.samuck21.uptaskbackend.dto.user.CreateUserRequest;
import com.samuck21.uptaskbackend.dto.user.CreateUserResponse;
import com.samuck21.uptaskbackend.dto.user.LoginRequest;
import com.samuck21.uptaskbackend.dto.user.LoginResponse;
import com.samuck21.uptaskbackend.models.Role;
import com.samuck21.uptaskbackend.models.UpdateUserRequest;
import com.samuck21.uptaskbackend.models.User;
import com.samuck21.uptaskbackend.models.UserHasRoles;
import com.samuck21.uptaskbackend.repositories.RoleRepository;
import com.samuck21.uptaskbackend.repositories.UserHasRolesRepository;
import com.samuck21.uptaskbackend.repositories.UserRepository;
import com.samuck21.uptaskbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.Phaser;

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

    @Autowired
    private JwtUtil jwtUtil;

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
    @Transactional
    public LoginResponse login(LoginRequest request){
     User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("el Email o Password no son validos"));
    if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
        throw  new RuntimeException("El Email o el Password  es valido");
    }
    String token = jwtUtil.genereteToken(user);
        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(),role.getName(),role.getImage(),role.getRoute()))
                .toList();
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(user.getId());
        createUserResponse.setName(user.getName());
        createUserResponse.setLastname(user.getLastname());
        createUserResponse.setImage(user.getImage());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setRoles(roleDTOS);
        LoginResponse response = new LoginResponse();
        response.setToken("Bearer "+token);
        response.setUser(createUserResponse);
        return  response;


    }

    @Transactional
    public CreateUserResponse findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El Email o Password no son validos"));

        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(),role.getName(),role.getImage(),role.getRoute()))
                .toList();
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(user.getId());
        createUserResponse.setName(user.getName());
        createUserResponse.setLastname(user.getLastname());
        createUserResponse.setImage(user.getImage());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setRoles(roleDTOS);
        return createUserResponse;
    }
    @Transactional
    public CreateUserResponse updateUserWithImage(Long id, UpdateUserRequest request) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El Email o Password no son validos"));

        if(request.getName() != null){
          user.setName(request.getName());
        }

        if(request.getLastname() != null){
            user.setLastname(request.getLastname());
        }

        if(request.getPhone() != null){
            user.setPhone(request.getPhone());
        }
        if(request.getFile() != null && request.getFile().isEmpty() ){
            String uploadDir = "uploads/users"+ user.getId();
            String filename = request.getFile().getOriginalFilename();
            String filePath = Paths.get(uploadDir,filename).toString();

            Files.createDirectories(Paths.get(uploadDir));
            Files.copy(request.getFile().getInputStream(),Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            user.setImage("/"+filename.replace("\\","/"));

        }
        userRepository.save(user);
        List<Role> roles = roleRepository.findAllByUserHasRoles_User_Id(user.getId());
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> new RoleDTO(role.getId(),role.getName(),role.getImage(),role.getRoute()))
                .toList();
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(user.getId());
        createUserResponse.setName(user.getName());
        createUserResponse.setLastname(user.getLastname());
        createUserResponse.setImage(user.getImage());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setRoles(roleDTOS);
        return createUserResponse;
    }


}
