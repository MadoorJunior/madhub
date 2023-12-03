package com.madoor.madhub.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("m_say")
public class Say {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private String ipSource;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
