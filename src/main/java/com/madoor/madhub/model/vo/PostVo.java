package com.madoor.madhub.model.vo;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "m_post", autoResultMap = true)
public class PostVo {
    Long id;
    String username;
    LocalDateTime createTime;
    String text;
    Integer comment;
    Integer thumbUp;
    Integer forward;
    Integer view;
    @TableField(typeHandler = JacksonTypeHandler.class)
    JSONObject media;
    Boolean isLiked;
    String avatar;
}
