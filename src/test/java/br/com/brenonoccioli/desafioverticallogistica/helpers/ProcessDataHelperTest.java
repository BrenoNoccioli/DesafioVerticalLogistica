package br.com.brenonoccioli.desafioverticallogistica.helpers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ProcessDataHelperTest {

    @Test
    void testConstructor(){
        //Apenas para cobertura de 100%
        assertDoesNotThrow(ProcessDataHelper::new);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, True",
             "0000000070                              Palmer Prosacco00000007530000000003     1836.742021030, False"})
    void testLineIdValid(String input, Boolean expected){
        assertEquals(expected, ProcessDataHelper.lineIsValid(input));
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, 70",
             "0000000000                              Palmer Prosacco00000007530000000003     1836.742021030,"})
    void testGetUserId(String input, Long expected){
        Long resp = ProcessDataHelper.getUserId(input);
        assertEquals(expected, resp);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, Palmer Prosacco",
             "0000000070                                             00000007530000000003     1836.742021030,"})
    void testGetUserName(String input, String expected){
        String resp = ProcessDataHelper.getUserName(input);
        assertEquals(expected, resp);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, 753",
             "0000000070                              Palmer Prosacco00000000000000000003     1836.7420210308,"})
    void testGetOrderId(String input, Long expected){
        Long resp = ProcessDataHelper.getOrderId(input);
        assertEquals(expected, resp);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, 3",
             "0000000070                              Palmer Prosacco00000007530000000000     1836.7420210308,"})
    void testGetProductId(String input, Long expected){
        Long resp = ProcessDataHelper.getProductId(input);
        assertEquals(expected, resp);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, 1836.74",
             "0000000070                              Palmer Prosacco00000007530000000003            20210308,"})
    void testGetProductValue(String input, BigDecimal expected){
        BigDecimal resp = ProcessDataHelper.getProductValue(input);
        assertEquals(expected, resp);
    }

    @ParameterizedTest
    @CsvSource(
            {"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308, 20210308",
             "0000000070                              Palmer Prosacco00000007530000000003     1836.7400000000,"})
    void testGetOrderDate(String input, String expected){
        String resp = ProcessDataHelper.getOrderDate(input);
        assertEquals(expected, resp);
    }
}