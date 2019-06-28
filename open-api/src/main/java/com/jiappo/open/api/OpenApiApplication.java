package com.jiappo.open.api;

import com.hummer.spring.plugin.context.init.HummerApplicationStart;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jiappo.open.api")
public class OpenApiApplication {

    public static void main(String[] args) {
        HummerApplicationStart.start(OpenApiApplication.class,args);
    }

}
