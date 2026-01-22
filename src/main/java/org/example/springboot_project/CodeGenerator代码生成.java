package org.example.springboot_project;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator代码生成 {

    // ======================== 常量配置区（可根据实际需求修改） ========================
    // 父包名（生成的代码会放在该包下）
    private static final String PARENT_PACKAGE = "org.example.springboot_project";

    // 需要生成代码的表名（支持多表，用数组表示）
    private static final String[] TABLE_NAMES = {"userinfo", "room", "repair" , "payment" ,  "notice" , "community"  , "building"};


    // 数据库连接信息
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_shequ?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // 作者（生成的代码注释注释会显示）
    private static final String AUTHOR = "admin";

    // 自定义Controller模板路径（基于resources目录）
    private static final String CUSTOM_CONTROLLER_TEMPLATE = "templates/controller.java.ftl";
    // =========================================================================

    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                // 全局配置
                .globalConfig(builder -> {
                    builder
                            // .author(AUTHOR)   //不显示作者
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")
                            // .commentDate("yyyy-MM-dd")  //不显示时间
                            .disableOpenDir();
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE)
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                // 模板配置
                .templateConfig(builder -> {
                    builder.controller(CUSTOM_CONTROLLER_TEMPLATE);
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAMES)
                            // 实体类配置
                            .entityBuilder()
                            .columnNaming(NamingStrategy.no_change)
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            // Controller配置
                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            // Service配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
