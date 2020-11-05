package com.aliceronascimento.cloud.temafinal.annotation;

import com.aliceronascimento.cloud.temafinal.operations.*;
import com.aliceronascimento.cloud.temafinal.service.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean (name = "SUM")
    @Scope(value = "prototype")
    public Sum sum(double num1, double num2) {
        return new Sum(num1, num2);
    }

    @Bean (name = "SUB")
    @Scope(value = "prototype")
    public Subtraction subtraction(double num1, double num2) {
        return new Subtraction(num1,num2);
    }

    @Bean(name = "DIV")
    @Scope(value = "prototype")
    public Division division(double num1, double num2) {
        return new Division(num1, num2);
    }

    @Bean (name = "MULT")
    @Scope(value = "prototype")
    public Multiplication multiplication(double num1, double num2) {
        return new Multiplication(num1, num2);
    }

    @Bean (name = "POW")
    @Scope(value = "prototype")
    public Pow pow(double num1, double num2) {
        return new Pow(num1, num2);
    }

    @Bean
    public Calculator calculate(){
        return new Calculator();
    }
}