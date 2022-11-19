package com.madoor.madhub.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.madoor.madhub.entity.User;
import com.madoor.madhub.mapper.UserMapper;
import com.madoor.madhub.model.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserMapper userMapper;
    public void register(UserRegisterDTO userRegisterDTO){
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .email(userRegisterDTO.getEmail())
                .password(SaSecureUtil.md5BySalt(userRegisterDTO.getPassword(), "madoorjunior"))
                .build();
        userMapper.insert(user);
    }
}
