package com.aliceronascimento.cloud.temafinal;

import com.aliceronascimento.cloud.temafinal.annotation.AppConfig;
import com.aliceronascimento.cloud.temafinal.annotation.HealthCheckConfig;
import com.aliceronascimento.cloud.temafinal.controller.HealthCheckResource;
import com.aliceronascimento.cloud.temafinal.controller.RxNettyHandler;
import com.aliceronascimento.cloud.temafinal.service.Calculator;
import io.reactivex.netty.RxNetty;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        Calculator cal= new Calculator();
        ApplicationContext acAppConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext acHealthCheck = new AnnotationConfigApplicationContext(HealthCheckConfig.class);
        HealthCheckResource healthCheck = (HealthCheckResource) acHealthCheck.getBean("healthCheckHandler");

        RxNetty.createHttpServer(8080, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheck),
                (Calculator) acAppConfig.getBean("calculate")))
                .startAndWait();
    }
}
