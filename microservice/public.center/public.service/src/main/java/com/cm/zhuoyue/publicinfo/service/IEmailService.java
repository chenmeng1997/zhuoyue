package com.cm.zhuoyue.publicinfo.service;

import java.util.Map;

/**
 * @author 陈萌
 * @date 2021/6/28 22:10
 */
public interface IEmailService {

    /**
     * 发送简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 正文
     */
    Boolean sendSimpleTextMail(String to, String subject, String content);

    /**
     * 发送 HTML 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 正文
     * @
     */
    Boolean sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param fileArr 附件
     */
    Boolean sendAttachmentMail(String to, String subject, String content, String... fileArr);

    /**
     * 发送带图片的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param rscId   图片名  rscPath路径
     * @
     */
    Boolean sendInlinkResourceMail(String to, String subject, String content, String rscPath, String rscId);

    /**
     * 发送模版邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param paramMap 参数
     * @param template 模板
     */
    Boolean sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template);

}
