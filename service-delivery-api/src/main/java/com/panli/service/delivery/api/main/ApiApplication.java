package com.panli.service.delivery.api.main;

import com.hummer.core.init.HummerApplicationStart;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
* application enter
* @author liguo
* @date 2019/7/11 14:55
* @since 1.0.0
**/
@SpringBootApplication(scanBasePackages = "com.panli.service.delivery")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApiApplication {

    public static void main(String[] args) {
        HummerApplicationStart.start(ApiApplication.class,args);
    }

}
