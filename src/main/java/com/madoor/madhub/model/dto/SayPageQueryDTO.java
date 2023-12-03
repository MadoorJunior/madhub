package com.madoor.madhub.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SayPageQueryDTO {
    @NotNull
    Integer current;
    @NotNull
    Integer pageSize;
}
