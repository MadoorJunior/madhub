package com.madoor.madhub.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.madoor.madhub.model.dto.UserLoginDTO;
import com.madoor.madhub.model.dto.UserRegisterDTO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResultVO<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userAuthService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResultVO<?> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return userAuthService.login(userLoginDTO);
    }

    @PostMapping("/logout")
    public ResultVO<?> logout() {
        StpUtil.logout();
        return ResultVO.ok();
    }

    @PostMapping("/info")
    public ResultVO<?> authInfo() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return ResultVO.ok(tokenInfo);
    }

    @GetMapping("/check")
    public  ResultVO<?> checkLogin() {
        System.out.println(StpUtil.isLogin());
        StpUtil.checkLogin();
        return ResultVO.ok();
    }
}
