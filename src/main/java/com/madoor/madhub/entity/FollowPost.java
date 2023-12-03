package com.madoor.madhub.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@TableName("m_follow_post")
@Data
@Accessors(chain = true)
public class FollowPost {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer userId;
    private Integer postId;
    private Integer replyTo;
    private String ipAddress;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableLogic
    private Boolean isDeleted;
    private Integer likes;
}
