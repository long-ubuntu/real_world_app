package com.long_project.real_worldapp.Service.Implement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.long_project.real_worldapp.Entity.User;
import com.long_project.real_worldapp.Model.user.DTO.userDTOResponse;
import com.long_project.real_worldapp.Model.user.DTO.userDTOloginRequest;
import com.long_project.real_worldapp.Model.user.Mapper.UserMapper;
import com.long_project.real_worldapp.Repository.UserRepository;
import com.long_project.real_worldapp.Service.UserService;
import com.long_project.real_worldapp.Util.JWTTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final JWTTokenUtil jwtTokenUtil;

    @Override
    public Map<String, userDTOResponse> authenticate(Map<String, userDTOloginRequest> userMap) {
        userDTOloginRequest userDTOloginRequest = userMap.get("user");
        Optional<User> userOptional = userRepository.findByEmail(userDTOloginRequest.getEmail());
        boolean isAuthen = false;
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getPassword().equals(userDTOloginRequest.getPassword())){
                isAuthen = true;
            }
        }
        if(!isAuthen){
            System.out.println("User name and password incorrect");
        }

        Map<String, userDTOResponse> wrapper = new HashMap<>();
        userDTOResponse userDTORe = UserMapper.toUserDTOResponse(userOptional.get());
        userDTORe.setToken(jwtTokenUtil.generateToken(userOptional.get(), 24*60*60));
        wrapper.put("user", userDTORe);
        return wrapper;
        
    }
    
}
