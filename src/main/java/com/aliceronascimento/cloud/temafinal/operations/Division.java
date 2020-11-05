package com.aliceronascimento.cloud.temafinal.operations;

import com.aliceronascimento.cloud.temafinal.exception.DivisionByZeroException;

public class Division implements Operation{

    private double num1, num2;

    public Division(double num1, double num2) {
        super();
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public double calculate() {
        if(num2 == 0) {
            throw new DivisionByZeroException("Impossible operation: division by zero!");
        }else {
            return num1/num2;
        }
    }

    @Override
    public double getNum1() {
        return this.num1;
    }

    @Override
    public double getNum2() {
        return this.num2;
    }

    public String toString() {
        return "\nOperation: "+num1+ " / " + num2 + " = " +calculate();
    }
}