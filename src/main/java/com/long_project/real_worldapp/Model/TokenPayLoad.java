package com.long_project.real_worldapp.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayLoad {
    private int userId;
    private String email;
}
