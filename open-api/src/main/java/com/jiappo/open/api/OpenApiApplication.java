package com.jiappo.open.api;

import com.hummer.spring.plugin.context.init.HummerApplicationStart;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.jiappo.open.api")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OpenApiApplication {

    public static void main(String[] args) {
        HummerApplicationStart.start(OpenApiApplication.class,args);
    }

}
