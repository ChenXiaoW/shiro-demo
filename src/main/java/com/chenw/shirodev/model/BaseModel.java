package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 响应模型
 *
 * @author chenw
 * @title: BaseModel
 * @date 2019/10/21 10:55
 */
@Data
public class BaseModel<T> {
    /** 响应码 */
    private Integer code;
    /** 响应结果 */
    private String result;
    /** 响应数据 */
    private T data;

    public BaseModel() {
    }

    public BaseModel(Integer code, String result, T data) {
        this.code = code;
        this.result = result;
        this.data = data;
    }
}
