package com.hero.wireless.http;

import com.hero.wireless.web.entity.send.Submit;

/**
 * 提交http回调
 * @author liuyanning
 * @date 2021年6月30日11:55:27
 */
public interface IHttpSendCallBack {
    /**
     * 成功回调
     * @param submit
     */
    void success(Submit submit);

    /**
     * 失败回调
     * @param submit
     */
    void failed(Submit submit);
}
