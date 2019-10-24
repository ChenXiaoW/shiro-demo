package com.chenw.shirodev.model;

import com.chenw.shirodev.utils.Constant;

/**
 * 失败响应模型
 *
 * @author chenw
 * @title: SuccessModel
 * @date 2019/10/21 10:57
 */
public class FailModel<T> extends BaseModel {

    public FailModel(T data){
        super(Constant.FAIL_CODE,Constant.FAIL_RESULT,data);
    }
}
