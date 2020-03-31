package com.raccoons.tda.api.util;

import com.raccoons.tda.api.value.PutCall;

/**
 * This class contains methods that are related to Options
 */
public class Options {

    // Option symbol format
    // Uppercase symbol, mm, d, yy, contract type, strike
    // Example AAPL_01119P190
    // Example AAPL_01019P190.5

    /**
     * This method is used to construct a option symbol
     *
     * @param putCall The contract type
     * @param symbol  The underlying symbol
     * @param strike  The strike price of the contract rounded to the nearest 0.5 dollar
     * @param month   The expiry month
     * @param day     The expiry day
     * @param year    The expire year
     * @return Returns the symbol of the option
     */
    public static String getSymbol(final PutCall putCall, final String symbol, final double strike,
                                   final int month, final int day, final int year) {
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
