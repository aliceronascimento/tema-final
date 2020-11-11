package com.aliceronascimento.cloud.temafinal.service;

import com.aliceronascimento.cloud.temafinal.exception.NullValueException;
import com.aliceronascimento.cloud.temafinal.operations.Operation;
import com.aliceronascimento.cloud.temafinal.operations.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private List<Operation> operationsHistory;
    @Autowired
    ApplicationContext ac;

    public Calculator() {
        this.operationsHistory = new ArrayList<>();
    }

    public Operation calculate(Double num1, OperationType operation, Double num2) {
        if(num1 == null || num2 == null || operation == null) {
            throw new NullValueException("Impossible operation: doesn't accept null values");
        }

        Operation operationresult = (Operation) ac.getBean(operation.getOperationType(), num1, num2);
        operationsHistory.add(operationresult);

        return operationresult;
    }

    public List<Operation> getHistory(){
        return operationsHistory;
    }
}