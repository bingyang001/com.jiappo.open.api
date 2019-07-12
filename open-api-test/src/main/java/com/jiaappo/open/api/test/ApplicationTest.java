package com.jiaappo.open.api.test;


import com.hummer.spring.plugin.context.init.HummerApplicationStart;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by liguo on 2017/6/23.
 */
@SpringBootApplication(scanBasePackages="com.jiaappo.open.api")
@PropertySource(value = {"classpath:application.properties","classpath:application-qa.properties"})
public class ApplicationTest {
    public static void main(String[] args) {
        HummerApplicationStart.start(ApplicationTest.class, args);
    }
}

