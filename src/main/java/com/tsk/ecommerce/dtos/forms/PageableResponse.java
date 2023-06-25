package com.tsk.ecommerce.dtos.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageableResponse<T> {
    private T data;
    private Integer page;
    private Integer parPage;
    private Integer total;
}
