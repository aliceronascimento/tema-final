package com.aliceronascimento.cloud.temafinal.operations;

public class Multiplication implements Operation{

    private double num1, num2;

    public Multiplication(double num1, double num2) {
        super();
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public double calculate() {
        return num1 * num2;
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
        return "\nOperation: "+num1+ " * " + num2 + " = " +calculate();
    }
}
