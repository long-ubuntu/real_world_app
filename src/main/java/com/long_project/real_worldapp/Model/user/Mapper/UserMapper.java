package com.long_project.real_worldapp.Model.user.Mapper;

import com.long_project.real_worldapp.Entity.User;
import com.long_project.real_worldapp.Model.user.DTO.userDTOResponse;

public class UserMapper {
    public static userDTOResponse toUserDTOResponse(User user){
        return userDTOResponse.builder().email(user.getEmail()).username(user.getUsername()).bio(user.getBio()).image(user.getImage()).build();
    }
}
