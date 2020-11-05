package com.aliceronascimento.cloud.temafinal.controller;

import com.aliceronascimento.cloud.temafinal.annotation.AppConfig;
import com.aliceronascimento.cloud.temafinal.operations.Operation;
import com.aliceronascimento.cloud.temafinal.operations.OperationType;
import com.aliceronascimento.cloud.temafinal.service.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    private ApplicationContext ac;
    private Calculator calculator;

    public CalculatorServlet () {
        this.ac = new AnnotationConfigApplicationContext(AppConfig.class);
        this.calculator = (Calculator) ac.getBean("calculate");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("Welcome to calculator!\n");

        if(request.getParameterMap().containsKey("num1") && request.getParameterMap().containsKey("num2") &&
                request.getParameterMap().containsKey("operation")) {

            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            OperationType operation = OperationType.valueOf(request.getParameter("operation"));
            Operation result = calculator.calculate(num1, operation, num2);

            out.println(result);
        }

        if(request.getParameterMap().containsKey("history")) {
            out.print(calculator.getHistory());
        }
        out.close();
    }
}