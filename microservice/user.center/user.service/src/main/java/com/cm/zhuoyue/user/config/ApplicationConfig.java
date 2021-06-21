package com.cm.zhuoyue.user.config;

import com.cm.zhuoyue.common.web.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈萌
 * @date 2021/6/21 16:55
 */
@Configuration
public class ApplicationConfig {
    @Autowired
    public void initUtil(ApplicationContext ctx){
        new SpringUtil().setApplicationContext(ctx);
    }
}
