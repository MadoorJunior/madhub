package com.madoor.madhub.model.dto;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class PostSaveDTO {
    private String content;
    private Boolean isAnonymous;
    private JSONObject media;
}
