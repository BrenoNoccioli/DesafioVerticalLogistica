package br.com.brenonoccioli.desafioverticallogistica.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.brenonoccioli.desafioverticallogistica.constants.ApplicationConstants.YYYY_MM_DD;

public class DateHelper {
    public static LocalDate convertStringtoLocalDate(String strDate, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(strDate, formatter);
    }

    public static String convertLocalDateToString(LocalDate date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static Boolean isValidDateParams(String initDate, String finishDate){
        boolean isValid = true;
        boolean hasInit = initDate != null && !initDate.isEmpty();
        boolean hasFinish = finishDate != null && !finishDate.isEmpty();

        if ((hasInit && !hasFinish) || (!hasInit && hasFinish)){
            isValid = false;
        }

        if (hasInit && hasFinish){
            if (initDate.length() == 10 && finishDate.length() == 10){
                LocalDate init = convertStringtoLocalDate(initDate, YYYY_MM_DD);
                LocalDate finish = convertStringtoLocalDate(finishDate, YYYY_MM_DD);
                isValid = init.isBefore(finish) || init.equals(finish);
            } else {
                isValid = false;
            }
        }

        return isValid;
    }

}
