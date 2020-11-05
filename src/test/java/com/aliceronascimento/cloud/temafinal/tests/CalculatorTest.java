package com.aliceronascimento.cloud.temafinal.tests;

import com.aliceronascimento.cloud.temafinal.annotation.AppConfig;
import com.aliceronascimento.cloud.temafinal.exception.DivisionByZeroException;
import com.aliceronascimento.cloud.temafinal.exception.NullValueException;
import com.aliceronascimento.cloud.temafinal.operations.OperationType;
import com.aliceronascimento.cloud.temafinal.service.Calculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void calculateSumTest() {
        double result = calculator.calculate(10.0, OperationType.SUM, 4.0).calculate();
        Assert.assertEquals(14.0, result, 0.0001);
    }

    @Test
    public void calculateSubtractionTest() {
        double result = calculator.calculate(14.0, OperationType.SUB, 4.0).calculate();
        Assert.assertEquals(10.0, result, 0.0001);
    }

    @Test
    public void calculateDivisionTest() {
        double result = calculator.calculate(14.0, OperationType.DIV, 2.0).calculate();
        Assert.assertEquals(7.0, result, 0.0001);
    }

    @Test
    public void calculateMultiplicationTest() {
        double result = calculator.calculate(2.0, OperationType.MULT, 7.0).calculate();
        Assert.assertEquals(14.0, result, 0.0001);
    }

    @Test
    public void calculatePowTest() {
        double result = calculator.calculate(3.0, OperationType.POW, 2.0).calculate();
        Assert.assertEquals(9.0, result, 0.0001);
    }

    @Test(expected = NullValueException.class)
    public void calculateOperationWithNullValueTest() {
        calculator.calculate(null, OperationType.SUM, null);
    }

    @Test(expected = NullValueException.class)
    public void calculateWithNullOperationTest() {
        calculator.calculate(7.0, null, 4.0);
    }

    @Test(expected = DivisionByZeroException.class)
    public void calculateDivionByZeroTest() {
        calculator.calculate(2.0, OperationType.DIV, 0.0);
    }

    @Test
    public void subtractionWithNegativeValueTest() {
        double result = calculator.calculate(-14.0, OperationType.SUB, -4.0).calculate();
        Assert.assertEquals(-10.0, result, 0.0001);
    }

    @Test
    public void divisionnWithNegativeValueTest() {
        double result = calculator.calculate(-14.0, OperationType.DIV, -2.0).calculate();
        Assert.assertEquals(7.0, result, 0.0001);
    }

    @Test
    public void powWithNegativeExponentTest() {
        double result = calculator.calculate(4.0, OperationType.POW, -2.0).calculate();
        Assert.assertEquals(0.0625, result, 0.0001);
    }

    @Test
    public void calculateSumWithLargeValueTest() {
        double result = 1.551264902E8;
        double operation = calculator.calculate(89654125.0, OperationType.SUM, 65472365.2).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateMultiplicationWithLargeValueTest() {
        double result = 5.86986761368645E15;
        double operation = calculator.calculate(89654125.0, OperationType.MULT, 65472365.2).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateDivisionWithLargeValueTest() {
        double result = 1.3679280482269267;
        double operation = calculator.calculate(895654125.0, OperationType.DIV, 654752365.2).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateSubtractionWithLargeValueTest() {
        double result = 1.3679280482269267;
        double operation = calculator.calculate(895654125.0, OperationType.DIV, 654752365.2).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateSumWithSmallValueTest() {
        double result = 8.01E-12;
        double operation = calculator.calculate(0.00000000000254, OperationType.SUM, .00000000000547).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateMultiplicationWithSmallValueTest() {
        double result = 1.17648E-12;
        double operation = calculator.calculate(0.00000000000258, OperationType.MULT, 0000000000000.456).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void calculateSubtractionWithSmallValueTest() {
        double result = -1.46949E-12;
        double operation = calculator.calculate(0.00000000000000051, OperationType.SUB, .00000000000147).calculate();

        Assert.assertEquals(result, operation, 0.0000000001);
    }

    @Test
    public void verifyifgetHistoryOperationTest() {
        calculator.calculate(10.0, OperationType.SUM, 4.0);
        calculator.calculate(17.0, OperationType.SUM, 3.0);
        calculator.calculate(1.0, OperationType.SUM, 8.0);

        Assert.assertEquals(10.0, calculator.getHistory().get(0).getNum1(), 0.0001);
        Assert.assertEquals(4.0, calculator.getHistory().get(0).getNum2(), 0.0001);

        Assert.assertEquals(17.0, calculator.getHistory().get(1).getNum1(),0.0001);
        Assert.assertEquals(3.0, calculator.getHistory().get(1).getNum2(),0.0001);

        Assert.assertEquals(1.0, calculator.getHistory().get(2).getNum1(),0.0001);
        Assert.assertEquals(8.0, calculator.getHistory().get(2).getNum2(),0.0001);

    }
}