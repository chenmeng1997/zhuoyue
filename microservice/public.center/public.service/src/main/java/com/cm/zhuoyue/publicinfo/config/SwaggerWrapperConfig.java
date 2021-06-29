package com.cm.zhuoyue.publicinfo.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 陈萌
 * @date 2021/6/21 15:14
 */
@Configuration
@EnableSwagger2
public class SwaggerWrapperConfig {

    @Autowired
    protected Environment env;

    /**
     * 可以定义多个组
     */
    @Bean
    public Docket createDocket() {

        String paths = env.getProperty("swagger.paths");
        String[] pathArray = paths.split(",");
        Predicate[] predicates = null;
        if (pathArray.length > 0) {
            predicates = new Predicate[pathArray.length];
            for (int i = 0; i < pathArray.length; i++) {
                String pathRegex = pathArray[i];
                predicates[i] = PathSelectors.regex(pathRegex);
            }
        }

//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        ticketPar.name("x-access-token").description("user token 用户令牌")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)//.globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
                .select()
//            .paths(PathSelectors.any())
                .paths(Predicates.or(predicates))//过滤的接口
                .build();
        docket.securitySchemes(securitySchemes());
        docket.securityContexts(securityContexts());
        return docket;

    }


    private ApiInfo apiInfo() {
        String appName = env.getProperty("spring.application.name");
        if (appName == null) {
            appName = "Demo rest api";
        }

        return new ApiInfoBuilder()
                .title(appName)//大标题
                .description(appName + " RESTful API Document")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("Zhejiang Rong Chuang Information Industry Co., Ltd.")
                .contact(new Contact("Zhejiang Rong Chuang Information Industry Co., Ltd.", "", "974213337@qq.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "x-access-token", "header");
        List<ApiKey> es = new ArrayList<>(3);
        es.add(apiKey);

        // 根据环境显示请求头标签
        boolean b = isInformalEnv();
        if (b) {
            es.add(new ApiKey("Authorization2", "token", "header"));
            es.add(new ApiKey("Authorization3", "userId", "header"));
        }

        return es;
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
        return Collections.singletonList(securityContext);
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> authorization = new ArrayList<>(3);
        authorization.add(new SecurityReference("Authorization", authorizationScopes));

        // 根据环境显示请求头标签
        boolean b = isInformalEnv();
        if (b) {
            authorization.add(new SecurityReference("Authorization2", authorizationScopes));
            authorization.add(new SecurityReference("Authorization3", authorizationScopes));
        }

        return authorization;
    }

    /**
     * 判断当前环境是否为非正式环境
     *
     * @return true是 false否
     */
    public boolean isInformalEnv() {
        String[] activeProfiles = env.getActiveProfiles();
        if (activeProfiles.length < 1) {
            return false;
        }

        List<String> envs = Arrays.asList("local", "dev", "test", "preProd");

        for (String activeProfile : activeProfiles) {
            if (envs.contains(activeProfile)) {
                return true;
            }
        }
        return false;
    }
}
