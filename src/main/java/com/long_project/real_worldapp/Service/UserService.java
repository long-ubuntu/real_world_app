package com.long_project.real_worldapp.Service;

import java.util.Map;

import com.long_project.real_worldapp.Model.user.DTO.userDTOResponse;
import com.long_project.real_worldapp.Model.user.DTO.userDTOcreate;
import com.long_project.real_worldapp.Model.user.DTO.userDTOloginRequest;

public interface UserService {

    Map<String, userDTOResponse> authenticate(Map<String, userDTOloginRequest> userMap);

    Map<String, userDTOResponse> creatUser(Map<String, userDTOcreate> userMap);
    
}
