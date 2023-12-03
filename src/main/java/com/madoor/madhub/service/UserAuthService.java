package com.madoor.madhub.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.madoor.madhub.entity.User;
import com.madoor.madhub.mapper.UserMapper;
import com.madoor.madhub.model.dto.UserLoginDTO;
import com.madoor.madhub.model.dto.UserRegisterDTO;
import com.madoor.madhub.model.vo.LoginVo;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.model.vo.TokenInfoVo;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserMapper userMapper;
    public ResultVO<?> register(UserRegisterDTO userRegisterDTO){
        Integer isExist = userMapper.selectCount(
                new LambdaQueryWrapper<User>()
                        .eq(User::getEmail, userRegisterDTO.getEmail())
                        .or()
                        .eq(User::getUsername, userRegisterDTO.getUsername())
        );

        if (isExist==0){
            String salt = RandomUtil.randomString(15);
            User user = User.builder()
                    .username(userRegisterDTO.getUsername())
                    .email(userRegisterDTO.getEmail())
                    .avatarUrl(userRegisterDTO.getAvatarUrl())
                    .salt(salt)
                    .password(SaSecureUtil.md5BySalt(userRegisterDTO.getPassword(),salt))
                    .userType(0)
                    .build();
            userMapper.insert(user);
            return ResultVO.ok();
        }
        return ResultVO.fail("用户名或邮箱已占用");
    }
    public ResultVO<?> login(UserLoginDTO userLoginDTO){
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getEmail, userLoginDTO.getEmail())
                        .or()
                        .eq(User::getUsername, userLoginDTO.getUsername())
        );
        if(ObjectUtil.isNull(user)){
            return ResultVO.fail("检查用户名、邮箱是否正确");
        }
        String salt = user.getSalt();
        String password = user.getPassword();
        if (password.equals(SaSecureUtil.md5BySalt(userLoginDTO.getPassword(),salt))){
            StpUtil.login(user.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            LoginVo loginVo = new LoginVo();
            loginVo.setAvatarUrl(user.getAvatarUrl());
            loginVo.setSaTokenInfo(tokenInfo);
            loginVo.setEmail(user.getEmail());
            loginVo.setUsername(user.getUsername());
            return ResultVO.ok(loginVo);
        }
        return ResultVO.fail("密码错误");
    }

}
