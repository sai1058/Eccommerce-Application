package com.example.calculatorappexample

import org.junit.Assert.*
import org.junit.Test

class MainViewModelTest {
    private val viewModel =  MainViewModel()

    @Test
    fun `testForArrayOfButtons different Possibilities`(){
        assertEquals("5", viewModel.arrayOfButton("5"))
        assertEquals("5.", viewModel.arrayOfButton("."))
        assertEquals("5.2", viewModel.arrayOfButton("2"))
        assertEquals("5.2", viewModel.arrayOfButton("."))

    }
    @Test
    fun `testForArrayOfOperations for Plus +`(){
        viewModel.arrayOfButton("5")
        viewModel.arrayOfOperations("+")
        assertEquals(5.0, viewModel.inputNum, 0.001)
        assertEquals("+", viewModel.selectedOp)

    }
    @Test
    fun `testForArrayOfOperations for negative -`(){
        viewModel.arrayOfButton("10")
        viewModel.arrayOfOperations("-")
        assertEquals(10.0, viewModel.inputNum, 0.001)
        assertEquals("-", viewModel.selectedOp)
    }
    @Test
    fun `testForArrayOfOperations for Multiply  `(){
        viewModel.arrayOfButton("10")
        viewModel.arrayOfOperations("*")
        assertEquals(10.0, viewModel.inputNum, 0.001)
        assertEquals("*", viewModel.selectedOp)
    }
    @Test
    fun `test for clear`() {
        viewModel.arrayOfButton("5")
        viewModel.arrayOfButton(".")
        viewModel.arrayOfButton("2")

        viewModel.clear()
        assertEquals("", viewModel.str)
        assertEquals(0.0, viewModel.inputNum, 0.001)
        assertEquals("", viewModel.selectedOp)
    }
    @Test
    fun `testArrayOfResult for operations for Plus +`() {
        viewModel.str = "5.2"
        viewModel.arrayOfOperations("+")
        viewModel.str = "3"
        val result = viewModel.arrayOfResult()
        assertEquals("8.2", result)
    }
    @Test
    fun `testArrayOfResult for operations for Subtraction -`() {
        viewModel.str = "10"
        viewModel.arrayOfOperations("-")
        viewModel.str = "2"
        val result = viewModel.arrayOfResult()
        assertEquals("8.0", result)
    }
    @Test
    fun `testArrayOfResult for operations for Division  `() {
        viewModel.str = "20"
        viewModel.arrayOfOperations("/")
        viewModel.str = "5"
        val result = viewModel.arrayOfResult()
        assertEquals("4.0", result)
    }
    @Test
    fun `testArrayOfResult for operations for Multiply `() {
        viewModel.str = "6"
        viewModel.arrayOfOperations("*")
        viewModel.str = "3"
        val result = viewModel.arrayOfResult()
        assertEquals("18.0", result)
    }
    @Test
    fun `testArrayOfResult for operations for Percentile `() {
        viewModel.str = "6"
        viewModel.arrayOfOperations("%")
        viewModel.str = "3"
        val result = viewModel.arrayOfResult()
        assertEquals("0.0", result)
    }
    @Test
    fun `testIsOperation is true when both values are present`() {
        viewModel.str = "5"
        viewModel.selectedOp = "+"
        assertEquals(true, viewModel.isOperation())

    }
    @Test
    fun `testOperation is false`(){
        viewModel.str = "6"
        viewModel.selectedOp = ""
        assertEquals(false, viewModel.isOperation())
    }
    @Test
    fun `testOperation is false when none of the values are present`(){
        viewModel.str = ""
        viewModel.selectedOp = ""
        assertEquals(false, viewModel.isOperation())
    }
    @Test
    fun `testAddition of two positive inputs`() {
        assertEquals(5.580, viewModel.addition(2.261, 3.32), 0.001)

    }
    @Test
    fun `testAddition of two negative inputs`() {
        assertEquals(-5.0, viewModel.addition(-2.0, -3.0), 0.001)

    }
    @Test
    fun `testAddition of one positive and one negative input`() {
        assertEquals(-1.0, viewModel.addition(2.0, -3.0), 0.001)

    }
    @Test
    fun `testAddition of one negative and one positive inputs`() {
        assertEquals(1.0, viewModel.addition(-2.0, 3.0), 0.001)

    }
    @Test
    fun `testSubraction of two positive inputs`(){
        assertEquals(10.0, viewModel.subtract(20.0, 10.0), 0.001)
    }
    @Test
    fun `testSubraction of two negative inputs`(){
        assertEquals(-10.0, viewModel.subtract(-20.0, -10.0), 0.001)
    }
    @Test
    fun `testSubraction of first positive and second negative inputs`(){
        assertEquals(30.0, viewModel.subtract(20.0, -10.0), 0.001)
    }
    @Test
    fun `testSubraction of first negative and second inputs`(){
        assertEquals(-30.0, viewModel.subtract(-20.0, 10.0), 0.001)
    }
    @Test
    fun `testmultiplication of two positive inputs`(){
        assertEquals(10.0, viewModel.multiply(2.0,5.0),0.001)
    }
    @Test
    fun `testmultiplication of two negative inputs`(){
        assertEquals(16.0, viewModel.multiply(-2.0,-8.0),0.001)
    }
    @Test
    fun `testmultiplication of first positive and second negative inputs`(){
        assertEquals(-25.0, viewModel.multiply(5.0,-5.0),0.001)
    }
    @Test
    fun `testmultiplication of fisrt negative and second positive inputs`(){
        assertEquals(-10.0, viewModel.multiply(-2.0,5.0),0.001)
    }
    @Test
    fun `testDivision of Two positive inputs`(){
        assertEquals(9.0, viewModel.divide(18.0,2.0), 0.001)
    }
    @Test
    fun `testDivision of Two negative inputs`(){
        assertEquals(10.0, viewModel.divide(-20.0,-2.0), 0.001)
    }
    @Test
    fun `testDivision of first positive and second negative input`(){
        assertEquals(-6.0, viewModel.divide(30.0,-5.0), 0.001)
    }
    @Test
    fun `testDivision of first negative and second positive inputs`(){
        assertEquals(-8.0, viewModel.divide(-48.0,6.0), 0.001)
    }
    @Test
    fun  `testModulus of two positive inputs`(){
        assertEquals(2.0, viewModel.percentile(2.0,3.0), 0.001)
    }
    @Test
    fun  `testModulus of two negative inputs`(){
        assertEquals(-2.0, viewModel.percentile(-2.0,-3.0), 0.001)
    }

    @Test
    fun  `testModulus of first positive and second negative inputs`(){
        assertEquals(0.0, viewModel.percentile(10.0,-2.0), 0.001)
    }
    @Test
    fun  `testModulus of first negative and second positive inputs`(){
        assertEquals(-6.0, viewModel.percentile(-20.0,7.0), 0.001)
    }
    @Test
    fun `testDivision for dividing any number by zero`(){
        assertEquals(Double.POSITIVE_INFINITY, viewModel.divide(20.0, 0.0), 0.01)
    }

}