package com.madoor.madhub.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryPageVO<T> {
    private Long curPage;
    private Long pageSize;
    private Long count;
    private Boolean hasNext;
    private Boolean hasPre;
    private List<T> records;
}
