package com.raccoons.tda.util;

import com.raccoons.tda.api.value.PutCall;

public class Options {

    // Option symbol format
    // Uppercase symbol, mm, d, yy, contract type, strike
    // Example AAPL_01119P190
    // Example AAPL_01019P190.5

    public static String getSymbol(PutCall putCall, String symbol, double strike, int month, int day, int year) {
        if (putCall == null || symbol == null || strike <= 0 || month <= 0 || day <= 0 || year <= 0) {
            return null;
        }
        final String strikePrice = (strike > 0 && strike / 0.5 == 0 ? String.format("%.1f", strike) : null);
        if (strikePrice != null) {
            final String mm = String.format("%02d", month);
            final String d = String.valueOf(day);
            final String yyyy = String.valueOf(year);
            final String yy = yyyy.length() > 2 ? yyyy.substring(yyyy.length() - 2) : yyyy;
            return symbol.toUpperCase() + "_" + mm + d + yy + (putCall.equals(PutCall.PUT) ? "P" : "C") + strikePrice;
        }
        return null;
    }
}
