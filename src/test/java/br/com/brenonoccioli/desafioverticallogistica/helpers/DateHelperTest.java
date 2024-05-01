package br.com.brenonoccioli.desafioverticallogistica.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateHelperTest {

    @Test
    void testConstructor(){
        assertDoesNotThrow(DateHelper::new);
    }

    @ParameterizedTest
    @CsvSource({"2024-04-30, yyyy-MM-dd, 2024-04-30",
            "20240430, yyyyMMdd, 2024-04-30"})
    void testConvertStringToLocalDate(String strDate, String format, LocalDate expected){
        LocalDate date = DateHelper.convertStringToLocalDate(strDate, format);
        assertEquals(expected, date);
    }

    @ParameterizedTest
    @CsvSource({"2024-04-30, yyyy-MM-dd, 2024-04-30",
            "2024-04-30, yyyyMMdd, 20240430"})
    void testConvertLocalDateToString(LocalDate date, String format, String expected){
        String strDate = DateHelper.convertLocalDateToString(date, format);
        assertEquals(expected, strDate);
    }

    @ParameterizedTest
    @CsvSource(value = {"2024-04-01, 2024-04-01, true",
            "2024-04-01, 2024-04-02, true",
            "'', '', true",
            "String.isEmpty(), String.isEmpty(), false",
            "'', 2024-04-01, false",
            "2024-04-01, ' ', false",
            "2024-04-01, 2024-03-30, false",
            "2024-04-0, 2024-04-02, false",
            "2024-04-01, 024-04-02, false",
            "2024-04-01, 024-04-02, false",
            "null, null, true",
            "null, 2024-04-01, false",
            "null, ' ', false",
            "2024-04-01, null, false",
            "' ', null, false",
    }, nullValues = "null")
    void testIsValidDateParams(String initDate, String finishDate, Boolean expected){
        Boolean isValid = DateHelper.isValidDateParams(initDate, finishDate);
        assertEquals(expected, isValid);
    }
}