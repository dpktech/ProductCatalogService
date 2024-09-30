package org.example.productcatalogservice_sept2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    //testFunctionName_when_then
    @Test
    public void testAdd_On2Integers_RunSucessfully() {
      //Arrange
       Calculator calculator  =  new Calculator();

      //Act
       int result = calculator.add(4,8);

       //Assert
       assert(result==12);
    }

    @Test
    public void testDivideByZero_ResultsInArithmeticExeption() {
       Calculator calculator  = new Calculator();

       //int res = calculator.divide(1,0);
       assertThrows(ArithmeticException.class,
               ()->calculator.divide(1,0)) ;
    }
}