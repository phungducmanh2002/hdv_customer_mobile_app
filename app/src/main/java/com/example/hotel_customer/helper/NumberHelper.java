package com.example.hotel_customer.helper;

import java.text.DecimalFormat;

public class NumberHelper {
    public static String FormatNumber(long number){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
    public static String GetMoney(long number) {
        DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
        return formatter.format(number);
    }
    public static String GetMoney(double number) {
        DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
        return formatter.format(number);
    }
}
