package com.chenw.shirodev.model;

import com.chenw.shirodev.utils.Constant;

/**
 * 成功响应模型
 *
 * @author chenw
 * @title: SuccessModel
 * @date 2019/10/21 10:57
 */
public class SuccessModel<T> extends BaseModel {

    public SuccessModel(T data){
        super(Constant.SUCCESS_CODE,Constant.SUCCESS_RESULT,data);
    }
}
