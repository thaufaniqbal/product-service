package com.iconnect.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponsePageDTO<T> {

    private List<?> list;
    private Integer page;
    private Integer resultPerPage;
    private Long totalResult;

}
