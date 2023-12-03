package com.madoor.madhub.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDTO {
    private String username;
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
}
