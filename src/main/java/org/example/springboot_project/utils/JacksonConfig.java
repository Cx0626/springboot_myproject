package org.example.springboot_project.utils;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson配置类
 * 用于定制Jackson的ObjectMapper序列化和反序列化规则，
 * 主要处理Java 8+时间类型（LocalDateTime）的格式化，统一日期时间格式为"yyyy-MM-dd HH:mm:ss"
 */
@Configuration
public class JacksonConfig {

    /**
     * 配置Jackson2ObjectMapperBuilder，注册JavaTimeModule并设置LocalDateTime的序列化/反序列化格式
     * 同时设置全局的SimpleDateFormat格式，兼容传统Date类型的处理
     *
     * @return 配置后的Jackson2ObjectMapperBuilder实例
     */
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        // 初始化JavaTimeModule，用于处理Java 8日期时间API类型
        JavaTimeModule module = new JavaTimeModule();
        // 定义日期时间格式化器，统一格式为"yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 为LocalDateTime类型注册自定义的反序列化器
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        // 为LocalDateTime类型注册自定义的序列化器
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

        // 构建Jackson2ObjectMapperBuilder，注册模块并设置全局日期格式
        return new Jackson2ObjectMapperBuilder()
                .modulesToInstall(module) // 安装自定义的JavaTimeModule
                .simpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置传统Date类型的格式化格式
    }
}
