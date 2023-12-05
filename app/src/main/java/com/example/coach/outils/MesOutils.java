package com.example.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe abstraite contenant différents outils
 */
public abstract class MesOutils {

    /**
     * Conversion d'une date du format String vers le format Date avec un format reçu
     * @param uneDate au format String
     * @param expectedPattern
     * @return la date au format date
     */
    public static Date convertStringToDate(String uneDate, String expectedPattern){
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Conversion d'une date du format String vers le format Date avec un format précis
     * @param uneDate au format String
     * @return la date au format date
     */
    public static Date convertStringToDate(String uneDate){
        return convertStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy");
    }

    /**
     * Conversion d'une date du format Date vers le format String
     * @param uneDate au format Date
     * @return la date au format String
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }

}