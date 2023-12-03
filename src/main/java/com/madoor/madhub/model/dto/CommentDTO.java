package com.madoor.madhub.model.dto;

import lombok.Data;

@Data
public class CommentDTO {
    String content;
    Integer userId;
    Integer postId;
    Integer replyTo;
}
