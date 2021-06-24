package com.cm.zhuoyue.publicInfo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈萌
 * @date 2021/6/24 23:18
 */
public class GeneratorCode {
    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //文件输出位置
        gc.setOutputDir("E:\\zhuoyue\\microservice\\public.center\\public.service\\src\\main\\java");//文件输出位置
        gc.setAuthor("陈萌");//作者
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        //打开
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/cm_usr?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("domain");
        pc.setXml("mapper");
        pc.setParent("com.cm.zhuoyue.publicinfo");//父目录
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略
        strategy.setInclude(new String[]{"sys_user_info"}); // 需要生成的表 多个英文逗号分割
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(false); //驼峰转连字符 requestMapper 用
        strategy.setEntityTableFieldAnnotationEnable(true); //是否生成实体时，生成字段注解
        //逻辑删除
        strategy.setLogicDeleteFieldName("IS_DELETE");
        //自动填充
        TableFill createField1 = new TableFill("GMT_CREATE", FieldFill.INSERT);
        TableFill createField2 = new TableFill("IS_DELETE", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("GMT_MODIFIED", FieldFill.UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(createField1);
        tableFillList.add(createField2);
        tableFillList.add(modifiedField);
        strategy.setTableFillList(tableFillList);
        //前缀
        //strategy.setTablePrefix("","");
        //strategy.setFieldPrefix();

        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //strategy.setTablePrefix(new String[] { "user_" });// 此处可以修改为您的表前缀
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类 你自己的父类实体,没有就不用设置!
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段 写于父类中的公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类 你自己的父类控制器,没有就不用设置!
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);

        mpg.setStrategy(strategy);
        mpg.execute();
    }

}
