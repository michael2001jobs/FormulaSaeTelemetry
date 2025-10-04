package br.com.michael_fausto.formulaSAE.util;

public class ConvertUtills {

    public static Integer intConvert(String intData) {
        if (intData == null || intData.isEmpty()) return null;
        try {
            return Integer.parseInt(intData);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double doubleConvert(String doubleData) {
        if (doubleData == null || doubleData.isEmpty()) return null;
        try {
            return Double.parseDouble(doubleData);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean booleanConvert(String value) {
        if (value == null || value.isEmpty()) return null;
        if (value.equalsIgnoreCase("true")) return true;
        if (value.equalsIgnoreCase("false")) return false;
        return null;
    }
}
