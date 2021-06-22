package com.cm.zhuoyue.common.web.enums;

import com.cm.zhuoyue.common.web.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 陈萌
 * @date 2021/6/21 23:24
 * 业务错误码
 */
public enum BizErrorCodeEnum implements ErrorCode {
    /**
     * 未指明的异常
     */
    UNSPECIFIED("500", "网络异常，请稍后再试"),
    NO_SERVICE("404", "网络异常, 服务器熔断"),
    FREQUENT_OPERATION("902", "请勿频繁操作"),

    // 通用异常
    REQUEST_ERROR("400", "入参异常,请检查入参后再次调用"),
    PAGE_NUM_IS_NULL("4001", "页码不能为空"),
    PAGE_SIZE_IS_NULL("4002", "页数不能为空"),
    TIME_FOEMAT_EXCEPTION("4003", "时间转换错误"),
    DOWNLOAD_EXCEPTION("4004", "下载文件异常"),
    DELETE_EXCEPTION("4005", "物理删除异常"),
    SERVICE_TYPE_IS_NULL("4006", "serviceType 不存在"),
    STATUS_ERROR("4007", "状态传入错误"),
    PARAM_ERROR("4008", "参数错误"),
    INSERT_ERROR("4009", "状态传入错误");
    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String description;

    /**
     * @param code        错误码
     * @param description 描述
     */
    BizErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     *
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
