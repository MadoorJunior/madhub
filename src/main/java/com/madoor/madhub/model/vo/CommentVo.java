package com.madoor.madhub.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVo {
    private Integer id;
    private String content;
    private Integer userId;
    private String username;
    private Integer postId;
    private Integer replyTo;
    private String ipAddress;
    private LocalDateTime createTime;
    private Integer likes;
    private Boolean isLiked;
    private String avatar;
}
