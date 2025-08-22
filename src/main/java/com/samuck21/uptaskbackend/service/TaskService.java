package com.samuck21.uptaskbackend.service;

import com.samuck21.uptaskbackend.dto.role.RoleDTO;
import com.samuck21.uptaskbackend.dto.user.*;
import com.samuck21.uptaskbackend.models.*;
import com.samuck21.uptaskbackend.repositories.RoleRepository;
import com.samuck21.uptaskbackend.repositories.TaskRepository;
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

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public CreateTaskResponse create(CreateTaskRequest request){

    Task task= new Task();
        task.setIncharge(request.incharge);
        task.setPersonnel(request.personnel);
        task.setStart_data(request.start_data);
        task.setFinish_data(request.finish_data);
        task.setDescription(request.description);


        Task saveTask = taskRepository.save(task);

        CreateTaskResponse response = new CreateTaskResponse();
        //response.setId(Long.valueOf(saveTask.getId()));
        response.setIncharge(saveTask.getIncharge());
        response.setPersonnel(saveTask.getPersonnel());
        response.setStart_data(saveTask.getStart_data());
        response.setFinish_data(saveTask.getFinish_data());
        response.setDescription(saveTask.getDescription());
       return  response;
    }


    @Transactional
    public CreateTaskResponse update(Long id, UpdateTaskRequest request) throws IOException {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No existe esa tarea"));



//        task.setPersonnel(request.getPersonnel());
//        task.setStart_data(request.getStart_data());
//        task.setFinish_data(request.getFinish_data());
//        task.setDescription(request.getDescription());

        if(request.getIncharge() != null){
            task.setIncharge(request.getIncharge());
        }

        if(request.getPersonnel() != null){
            task.setPersonnel(request.getPersonnel());
        }

        if(request.getStart_data() != null){
            task.setStart_data(request.getStart_data());
        }
        if(request.getFinish_data() != null){
            task.setFinish_data(request.getFinish_data());
        }
        if(request.getDescription() != null){
            task.setDescription(request.getDescription());
        }



        taskRepository.save(task);

        CreateTaskResponse createTaskResponse = new CreateTaskResponse();
        createTaskResponse.setId(task.getId());
        createTaskResponse.setIncharge(task.getIncharge());
        createTaskResponse.setPersonnel(task.getPersonnel());
        createTaskResponse.setStart_data(task.getStart_data());
        createTaskResponse.setFinish_data(task.getFinish_data());
        createTaskResponse.setDescription(task.getDescription());

        return createTaskResponse;
    }
    @Transactional
    public void delete(Long  id){
        taskRepository.deleteById(id);
    }
    @Transactional
    public List<Task> readAll(){
        return taskRepository.findAll();

    }
}
