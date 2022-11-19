package com.madoor.madhub.controller;

import com.madoor.madhub.model.dto.UserRegisterDTO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;
    @PostMapping("/register")
    public ResultVO<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userAuthService.register(userRegisterDTO);
        return ResultVO.ok();
    }
}
