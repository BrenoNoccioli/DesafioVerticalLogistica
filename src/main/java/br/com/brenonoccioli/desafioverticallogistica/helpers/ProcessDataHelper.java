package br.com.brenonoccioli.desafioverticallogistica.helpers;

import java.math.BigDecimal;

public class ProcessDataHelper {

    public static Boolean lineIsValid(String line){
        return line.chars().count() == 95;
    }

    public static Long getUserId(String line){
        String userId = line.substring(0, 10);
        String cleaned = cleanZeroBefore(userId);
        return cleaned != null ? Long.valueOf(cleaned) : null;
    }

    public static String getUserName(String line){
        String userName = line.substring(10, 55);
        return cleanWhiteSpaceBefore(userName);
    }

    public static Long getOrderId(String line){
        String orderId = line.substring(55, 65);
        String cleaned = cleanZeroBefore(orderId);
        return cleaned != null ? Long.valueOf(cleaned) : null;
    }

    public static Long getProductId(String line){
        String productId = line.substring(65, 75);
        String cleaned = cleanZeroBefore(productId);
        return cleaned != null ? Long.valueOf(cleaned) : null;
    }

    public static BigDecimal getProductValue(String line){
        String productValue = line.substring(75, 87);
        String cleaned = cleanWhiteSpaceBefore(productValue);
        return cleaned != null ? new BigDecimal(cleaned) : null;
    }

    public static String getOrderDate(String line){
        String orderDate = line.substring(87, 95);
        return cleanZeroBefore(orderDate);
    }

    private static String cleanZeroBefore(String str) {
        String cleanStr = str.replaceFirst("^[0\\s]+", "");
        return fieldIsValid(cleanStr) ? cleanStr : null;
    }

    private static String cleanWhiteSpaceBefore(String str) {
        String cleanStr = str.replaceFirst("^['\\s]+", "");
        return fieldIsValid(cleanStr) ? cleanStr : null;
    }

    private static Boolean fieldIsValid(String cleanStr) {
        return cleanStr != null && !cleanStr.isBlank();
    }
}

