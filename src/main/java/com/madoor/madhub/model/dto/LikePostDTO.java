package com.madoor.madhub.model.dto;

import lombok.Data;

@Data
public class LikePostDTO {
    String postId;
    String userId;
    Boolean isLiked;
}
