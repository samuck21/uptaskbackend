package com.samuck21.uptaskbackend.controllers;

import com.samuck21.uptaskbackend.dto.user.*;
import com.samuck21.uptaskbackend.models.Store;
import com.samuck21.uptaskbackend.models.Task;
import com.samuck21.uptaskbackend.service.StoreService;
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
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateStoreRequest request) {

        try {
            CreateStoreResponse response = storeService.create(request);
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
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute UpdateStoreRequest request) {

        try {
            CreateStoreResponse response = storeService.update(id,request);
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
        storeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public  ResponseEntity<List<Store>> findAll(){
        List<Store> store = storeService.readAll();
        return ResponseEntity.ok(store);
    }



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
