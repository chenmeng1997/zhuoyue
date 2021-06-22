package com.cm.zhuoyue.common.web.exception;

/**
 * @author 陈萌
 * @date 2021/6/21 23:22
 */
/**
 * 错误码接口
 * @author jacques
 * @version $Id: ErrorCode.java, v 0.1 2016年4月28日 下午3:50:57 jacques Exp $
 */
public interface ErrorCode {

    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getDescription();
}
