package com.jiaappo.open.api.test;


import com.hummer.spring.plugin.context.init.HummerApplicationStart;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by liguo on 2017/6/23.
 */
@SpringBootApplication(scanBasePackages="com.jiaappo.open.api")
public class ApplicationTest {
    public static void main(String[] args) {
        HummerApplicationStart.start(ApplicationTest.class, args);
    }
}

