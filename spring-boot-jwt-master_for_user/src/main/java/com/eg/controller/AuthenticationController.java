package com.eg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.eg.config.JwtTokenUtil;
import com.eg.model.*;
import com.eg.service.UserService;

import java.util.List;

import static com.eg.model.Constants.TOKEN_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    private final Logger Log = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        Log.info("username is"+loginUser.getUsername());
        final User user = userService.findOne(loginUser.getUsername());
        Log.info("username is"+user.getUsername()+"\t password is "+user.getPassword());
        final String token = jwtTokenUtil.generateToken(user);
        System.out.println("Role is:"+user.getRole());
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername(), user.getRole()));
    }


}
