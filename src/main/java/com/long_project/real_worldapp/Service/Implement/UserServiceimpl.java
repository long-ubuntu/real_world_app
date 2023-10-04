package com.long_project.real_worldapp.Service.Implement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.long_project.real_worldapp.Entity.User;
import com.long_project.real_worldapp.Model.user.DTO.userDTOResponse;
import com.long_project.real_worldapp.Model.user.DTO.userDTOcreate;
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
        return buildDTOResponse(userOptional.get());
        
    }

    @Override
    public Map<String, userDTOResponse> creatUser(Map<String, userDTOcreate> userMap) {
        userDTOcreate userDTOcreate = userMap.get("user");
        User user = UserMapper.toUser(userDTOcreate);
        user = userRepository.save(user);
        return buildDTOResponse(user);
    }

    private Map<String, userDTOResponse> buildDTOResponse(User user){
        Map<String, userDTOResponse> wrapper = new HashMap<>();
        userDTOResponse userDTORe = UserMapper.toUserDTOResponse(user);
        userDTORe.setToken(jwtTokenUtil.generateToken(user, 24*60*60));
        wrapper.put("user", userDTORe);
        return wrapper;
        
    } 
    
}
