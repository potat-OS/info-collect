package com.yb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jue-PC
 */
@Configuration
@ComponentScan(basePackages = "com.yb")
@Import({SpringMybatisConfig.class, SpringMvcConfig.class})
public class SpringConfig {
}
