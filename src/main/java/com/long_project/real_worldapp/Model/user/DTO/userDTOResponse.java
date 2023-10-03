package com.long_project.real_worldapp.Model.user.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class userDTOResponse {

    private String email;

    private String token;

    private String username;

    private String bio;

    private String image;
}
    
