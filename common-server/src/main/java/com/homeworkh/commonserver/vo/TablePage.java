package com.homeworkh.commonserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Map;

/**
 * @author lq
 * @date 2020/12/18 10:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablePage {
    @NonNull
    private Integer start;
    @NonNull
    private Integer length;
    private Integer draw;
    private Map<String,Object> param;
}
