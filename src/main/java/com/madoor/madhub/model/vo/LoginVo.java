package com.madoor.madhub.model.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.madoor.madhub.entity.User;
import lombok.Data;

@Data
public class LoginVo {
    SaTokenInfo saTokenInfo;
    String avatarUrl;
    String username;
    String email;
}
