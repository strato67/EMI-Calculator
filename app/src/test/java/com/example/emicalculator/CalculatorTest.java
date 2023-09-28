package com.example.emicalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorTest {


    @Test
    public void checkGetSet(){

        PaymentCalculation paymentCalculation = new PaymentCalculation();
        paymentCalculation.setValues("20000", "4.5", "25");
        String[] result = paymentCalculation.getValues();
        String[] expectedArray = {"20000.0", "4.5", "25.0"};
        assertArrayEquals(expectedArray, result);

    }
    @Test
    public void calculateTest(){
        PaymentCalculation paymentCalculation = new PaymentCalculation();
        paymentCalculation.setValues("30000", "1", "2");
        double result = paymentCalculation.calculatePayment();
        assertEquals(1263, result, 2);
    }

}


