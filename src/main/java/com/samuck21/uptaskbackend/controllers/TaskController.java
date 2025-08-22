package com.samuck21.uptaskbackend.controllers;

import com.samuck21.uptaskbackend.dto.user.*;
import com.samuck21.uptaskbackend.models.Task;
import com.samuck21.uptaskbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
 @CrossOrigin
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateTaskRequest request) {

        try {
            CreateTaskResponse response = taskService.create(request);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("message", e.getMessage(),
                            "statusCode",HttpStatus.UNAUTHORIZED.value()

                    )
            );
        }
    }

    @PutMapping(value = "/upload/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute UpdateTaskRequest request) {

        try {
            CreateTaskResponse response = taskService.update(id,request);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", e.getMessage(),
                            "statusCode",HttpStatus.NOT_FOUND.value()

                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", e.getMessage(),
                            "statusCode",HttpStatus.INTERNAL_SERVER_ERROR.value()

                    )
            );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public  ResponseEntity<List<Task>> findAll(){
        List<Task> task = taskService.readAll();
        return ResponseEntity.ok(task);
    }
//    @GetMapping
//    public  ResponseEntity<Task> findAll(){
//        final List<Task> task = taskService.readAll();
//        return  task != null && !task.isEmpty()
//                ? new ResponseEntity<>(taskService,HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//
//        try {
//            CreateUserResponse response = userService.findById(id);
//            return ResponseEntity.ok(response);
//        }catch (RuntimeException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    Map.of("message", e.getMessage(),
//                            "statusCode",HttpStatus.NOT_FOUND.value()
//
//                    )
//            );
//        }
//
//
//    }

//    @PutMapping(value = "/upload/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute UpdateUserRequest request) {
//
//        try {
//            CreateUserResponse response = userService.updateUserWithImage(id,request);
//            return ResponseEntity.ok(response);
//        }catch (RuntimeException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    Map.of("message", e.getMessage(),
//                            "statusCode",HttpStatus.NOT_FOUND.value()
//
//                    )
//            );
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    Map.of("message", e.getMessage(),
//                            "statusCode",HttpStatus.INTERNAL_SERVER_ERROR.value()
//
//                    )
//            );
//        }
//
//
//    }

}
