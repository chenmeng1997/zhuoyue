package com.cm.zhuoyue.common.web.annotation.method;

/**
 * @author 陈萌
 * @date 2021/6/21 22:57
 */

import com.cm.zhuoyue.common.web.constants.BaseResultCode;
import lombok.Data;

/**
 * 通用响应
 *
 * @param <T>
 */
@Data
public class GenericResponse<T> {

    // 错误类型
    private String code;
    private String message;
    // 错误类型，如果是BUSI_ERROR，则为业务异常，业务异常不打印异常日志。
    private String errorType;
    private T result;


    public GenericResponse() {
        super();
    }

    public GenericResponse(T result) {
        this(BaseResultCode.SUCCESS, BaseResultCode.SUCCESS_MSG, result);
    }

    public GenericResponse(String code,
                           String message, T result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public GenericResponse(String requestId, String code,
                           String message, T result, String errorType) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
        this.errorType = errorType;
    }

    /**
     * 验证返回是否正常
     */
    /*@JSONField(serialize = false, deserialize = false)
    @Transient
    public void assertSuccess() {

        if (TransErrorCodeEnum.contains(this.getCode())) {
            throw new BusinessException(TransErrorCodeEnum.getByCode(this.getCode()).getValue(), this.getMessage());
        }
        if (BaseResultCode.SYSTEM_ERROR.equals(this.getCode())) {
            throw new SystemException(this.getCode(), this.getMessage());
        } else if (!BaseResultCode.SUCCESS.equals(this.getCode())) {
            throw new BusinessException(this.getCode(), this.getMessage());
        }
    }*/

    /**
     * 验证返回是否正常（该方法禁止修改）
     */
   /* @JSONField(serialize = false, deserialize = false)
    @Transient
    public void assertSuccessNoChange() {
        if (BaseResultCode.SYSTEM_ERROR.equals(this.getCode())) {
            throw new SystemException(this.getCode(), this.getMessage());
        } else if (!BaseResultCode.SUCCESS.equals(this.getCode())) {
            throw new BusinessException(this.getCode(), this.getMessage(), this.result);
        }
    }*/

    /**
     * 校验并返回结果
     *
     * @return 结果
     */
    /*@Transient
    @JSONField(serialize = false, deserialize = false)
    public T getResultSuccess() {
        assertSuccess();
        return this.getResult();
    }*/

    /**
     * 不转换，直接抛出微服务中的异常（该方法禁止修改）
     *
     * @return 结果
     */
  /*  @Transient
    @JSONField(serialize = false, deserialize = false)
    public T resultSuccess() {
        assertSuccessNoChange();
        return this.getResult();
    }*/

    public boolean isSuccess() {
        return BaseResultCode.SUCCESS.equals(this.code);
    }

   /* public static void assertResponse(GenericResponse response) {
        if (!response.getCode().equals(BaseResultCode.SUCCESS)) {
            BizErrorCodeEnum codeEnum = BizErrorCodeEnum.getByCode(response.getCode());
            throw new BizException(codeEnum);
        }
    }*/

    public boolean isNotSuccess() {
        return !isSuccess();
    }
}