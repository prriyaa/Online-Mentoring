package com.eg.controller;

import com.eg.config.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eg.dao.UserDao;
import com.eg.model.ApiResponse;
import com.eg.model.User;
import com.eg.model.UserDto;
import com.eg.service.UserService;

import java.util.List;

import static com.eg.model.Constants.TOKEN_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDao userdao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final Logger Log = LoggerFactory.getLogger(getClass());
    @PostMapping
    public ApiResponse<User> saveUser(@RequestBody UserDto user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @GetMapping
    //public ApiResponse<List<User>> listUser(){
    public Iterable<User> listUser(){
        return userdao.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }

    @GetMapping( "/authorizing")
    public ResponseEntity<String> getRole(@RequestHeader("Authorization") String header) {
        System.out.println("header is"+header);
        String authToken = header.replace(TOKEN_PREFIX,"");
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User usr=userService.findByUsername(username);
        String role=usr.getRole();
        return new ResponseEntity<String>(role,new HttpHeaders(), HttpStatus.OK); }



}
