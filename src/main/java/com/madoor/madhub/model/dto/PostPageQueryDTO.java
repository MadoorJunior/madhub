package com.madoor.madhub.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostPageQueryDTO {
    @NotNull
    private Integer curPage;
    @NotNull
    private Integer pageSize;
}
