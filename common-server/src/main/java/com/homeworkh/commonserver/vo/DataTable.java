package com.homeworkh.commonserver.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lq
 * @date 2020/12/18 10:21
 */
@Data
public class DataTable<T> {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
}
