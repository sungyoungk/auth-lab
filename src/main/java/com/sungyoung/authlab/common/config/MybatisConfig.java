package com.sungyoung.authlab.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sungyoung.authlab.member.mapper")
public class MybatisConfig {
}
