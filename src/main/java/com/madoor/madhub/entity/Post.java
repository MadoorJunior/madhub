package com.madoor.madhub.entity;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName(value = "m_post", autoResultMap = true)
public class Post {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer userId;
    private String ipAddress;
    private String ipSource;
    private Boolean isAnonymous;
    private Integer thumbUp;
    private Integer sucks;
    private Integer comment;
    private Integer forward;
    private Integer view;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject media;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Boolean isDeleted;
}
