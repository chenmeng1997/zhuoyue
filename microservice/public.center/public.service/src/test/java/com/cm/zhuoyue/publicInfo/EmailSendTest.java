package com.cm.zhuoyue.publicInfo;

import com.cm.zhuoyue.publicinfo.service.IEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈萌
 * @date 2021/6/28 22:56
 */
@SpringBootTest
public class EmailSendTest {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    //发送简单文本邮件
    @Test
    public void sendSimpleMailTest() {
        sendSimpleMail();
    }

    public void sendSimpleMail(){
        emailService.sendSimpleTextMail("321502531@qq.com","测试spring boot imail-主题","测试spring boot imail - 内容");
    }
    //发送 HTML 邮件
    @Test
    public void sendHtmlMail() throws MessagingException {

        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<body>\n" +
                "</html>\n";

        emailService.sendHtmlMail("321502531@qq.com","这是一封HTML邮件",content);
    }

    //发送带附件的邮件
    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "F:/百度云下载内容/新建文本文档.txt";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<h1>附件传输</h1>\n" +
                "<body>\n" +
                "</html>\n";
        emailService.sendAttachmentMail("321502531@qq.com","这是一封HTML邮件",content, filePath);
    }

    //发送带图片的邮件
    @Test
    public void sendInlinkResourceMail() throws MessagingException {
        //TODO 改为本地图片目录
        String imgPath = "F:/背景/a4.jpg";
        String rscId = "a4.jpg";
        String content = "<html>" +
                "<body>" +
                "<h1>图片邮件</h1>" +
                "<img src='cid:"+rscId+"'></img>图" +
                "<body>" +
                "</html>";

        Map<String, String> map = new HashMap<>();
        map.put(rscId,imgPath);
        emailService.sendInlinkResourceMail("321502531@qq.com","这是一封图片邮件",content, imgPath, rscId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        map.put("id","220");
        emailService.sendTemplateMail("321502531@qq.com","这是一封HTML模板邮件",map,"emailTeplate");
    }

}
