/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahim
 */
public class HelperFunctions {

    public static List<String> padListWith(String padder, int howManyMax, List<String> listToPad) {
        List<String> list = new ArrayList<>();

        if (listToPad.size() >= howManyMax) {
            return listToPad;
        }
        //else
        for (int i = 0; i < listToPad.size(); i++) {
            list.add(listToPad.get(i));
        }
        //Now add the padded ones
        for (int i = listToPad.size(); i <= howManyMax; i++) {
            list.add(padder);
        }

        return list;
    }

    public static String getYesOrNo(String boolValue) {
        if(boolValue == null){
            return "NO";
        }
        if (boolValue.trim().contains("0")) {
            return "NO";
        } else if (boolValue.trim().equals("null")) {
            return "NO";
        } else {
            return "YES";
        }

    }

    public static String getPercentage(String valStr) {
        if(valStr == null){
            return "0.0";
        }
        double valDouble;
        try {
            valDouble = Double.parseDouble(valStr);
            double percent = valDouble * 100.0;
            return String.valueOf(percent);
        } catch (NumberFormatException e) {
            return valStr;
        }

    }

    public static void printListNoLine(List<String> ip_address) {
        ip_address.forEach((x) -> {
            System.out.print(x + " ");
        });
        System.out.println("");
    }

}
