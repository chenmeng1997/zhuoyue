package com.cm.zhuoyue.publicinfo.service.impl;

import com.cm.zhuoyue.common.web.enums.BizErrorCodeEnum;
import com.cm.zhuoyue.common.web.exception.BizException;
import com.cm.zhuoyue.publicinfo.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author 陈萌
 * @date 2021/6/28 22:23
 */
@Service
public class EmailServiceImpl implements IEmailService {

    //@Value("${spring.mail.username}")
    private String from = "2322051194@qq.com";

    //@Value("${spring.mail.password}")
    private String pass = "spring.mail.password";


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Boolean sendSimpleTextMail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            message.setFrom(from);
            mailSender.send(message);
        } catch (Exception e) {
            throw new BizException(BizErrorCodeEnum.EMAIL_SEND_EXCEPTION);
        }
        return true;
    }

    @Override
    public Boolean sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            // true 为 HTML 邮件
            messageHelper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BizException(BizErrorCodeEnum.EMAIL_SEND_EXCEPTION);
        }
        return true;
    }

    @Override
    public Boolean sendAttachmentMail(String to, String subject, String content, String... fileArr) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            // 添加附件
            for (String filePath : fileArr) {
                FileSystemResource fileResource = new FileSystemResource(new File(filePath));
                if (fileResource.exists()) {
                    String filename = fileResource.getFilename();
                    messageHelper.addAttachment(filename, fileResource);
                }
            }
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new BizException(BizErrorCodeEnum.EMAIL_SEND_EXCEPTION);
        }
        return true;
    }

    @Override
    public Boolean sendInlinkResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template) {
        Context context = new Context();
        // 设置变量的值
        context.setVariables(paramMap);
        String emailContent = templateEngine.process(template, context);
        return sendHtmlMail(to, subject, emailContent);
    }

}
