package com.madoor.madhub.controller;

import com.madoor.madhub.model.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserAuthController {
    @PostMapping("/register")
    public ResultVO<?> register(@RequestBody String s){
        return ResultVO.ok();
    }
}
