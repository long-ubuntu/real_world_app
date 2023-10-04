package com.long_project.real_worldapp.Model.user.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class userDTOcreate {
    private String userName;

    private String email;

    private String passWord;
}
