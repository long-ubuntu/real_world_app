package com.long_project.real_worldapp.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.long_project.real_worldapp.Model.user.DTO.userDTOResponse;
import com.long_project.real_worldapp.Model.user.DTO.userDTOloginRequest;
import com.long_project.real_worldapp.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    
    @PostMapping(value = "/users/login")
    public Map<String, userDTOResponse> login(@RequestBody Map<String, userDTOloginRequest> userMap){
        return userService.authenticate(userMap);
    }
    
}
