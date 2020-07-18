package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fundamental {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("high52")
    private Double high52;

    @JsonProperty("low52")
    private Double low52;

    @JsonProperty("dividendAmount")
    private Double dividendAmount;

    @JsonProperty("dividendYield")
    private Double dividendYield;

    @JsonProperty("dividendDate")
    private String dividendDate;

    @JsonProperty("peRatio")
    private Double peRatio;

    @JsonProperty("pegRatio")
    private Double pegRatio;

    @JsonProperty("pbRatio")
    private Double pbRatio;

    @JsonProperty("prRatio")
    private Double prRatio;

    @JsonProperty("pcfRatio")
    private Double pcfRatio;

    @JsonProperty("grossMarginTTM")
    private Double grossMarginTTM;

    @JsonProperty("grossMarginMRQ")
    private Double grossMarginMRQ;

    @JsonProperty("netProfitMarginTTM")
    private Double netProfitMarginTTM;

    @JsonProperty("netProfitMarginMRQ")
    private Double netProfitMarginMRQ;

    @JsonProperty("operatingMarginTTM")
    private Double operatingMarginTTM;

    @JsonProperty("operatingMarginMRQ")
    private Double operatingMarginMRQ;

    @JsonProperty("returnOnEquity")
    private Double returnOnEquity;

    @JsonProperty("returnOnAssets")
    private Double returnOnAssets;

    @JsonProperty("returnOnInvestment")
    private Double returnOnInvestment;

    @JsonProperty("quickRatio")
    private Double quickRatio;

    @JsonProperty("currentRatio")
    private Double currentRatio;

    @JsonProperty("interestCoverage")
    private Double interestCoverage;

    @JsonProperty("totalDebtToCapital")
    private Double totalDebtToCapital;

    @JsonProperty("ltDebtToEquity")
    private Double ltDebtToEquity;

    @JsonProperty("totalDebtToEquity")
    private Double totalDebtToEquity;

    @JsonProperty("epsTTM")
    private Double epsTTM;

    @JsonProperty("epsChangePercentTTM")
    private Double epsChangePercentTTM;

    @JsonProperty("epsChangeYear")
    private Double epsChangeYear;

    @JsonProperty("epsChange")
    private Double epsChange;

    @JsonProperty("revChangeYear")
    private Double revChangeYear;

    @JsonProperty("revChangeTTM")
    private Double revChangeTTM;

    @JsonProperty("revChangeIn")
    private Double revChangeIn;

    @JsonProperty("sharesOutstanding")
    private Double sharesOutstanding;

    @JsonProperty("marketCapFloat")
    private Double marketCapFloat;

    @JsonProperty("marketCap")
    private Double marketCap;

    @JsonProperty("bookValuePerShare")
    private Double bookValuePerShare;

    @JsonProperty("shortIntToFloat")
    private Double shortIntToFloat;

    @JsonProperty("shortIntDayToCover")
    private Double shortIntDayToCover;

    @JsonProperty("divGrowthRate3Year")
    private Double divGrowthRate3Year;

    @JsonProperty("dividendPayAmount")
    private Double dividendPayAmount;

    @JsonProperty("dividendPayDate")
    private String dividendPayDate;

    @JsonProperty("beta")
    private Double beta;

    @JsonProperty("vol1DayAvg")
    private Double vol1DayAvg;

    @JsonProperty("vol10DayAvg")
    private Double vol10DayAvg;

    @JsonProperty("vol3MonthAvg")
    private Double vol3MonthAvg;

    public String getSymbol() {
        return symbol;
    }

    public Double getHigh52() {
        return high52;
    }

    public Double getLow52() {
        return low52;
    }

    public Double getDividendAmount() {
        return dividendAmount;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public String getDividendDate() {
        return dividendDate;
    }

    public Double getPeRatio() {
        return peRatio;
    }

    public Double getPegRatio() {
        return pegRatio;
    }

    public Double getPbRatio() {
        return pbRatio;
    }

    public Double getPrRatio() {
        return prRatio;
    }

    public Double getPcfRatio() {
        return pcfRatio;
    }

    public Double getGrossMarginTTM() {
        return grossMarginTTM;
    }

    public Double getGrossMarginMRQ() {
        return grossMarginMRQ;
    }

    public Double getNetProfitMarginTTM() {
        return netProfitMarginTTM;
    }

    public Double getNetProfitMarginMRQ() {
        return netProfitMarginMRQ;
    }

    public Double getOperatingMarginTTM() {
        return operatingMarginTTM;
    }

    public Double getOperatingMarginMRQ() {
        return operatingMarginMRQ;
    }

    public Double getReturnOnEquity() {
        return returnOnEquity;
    }

    public Double getReturnOnAssets() {
        return returnOnAssets;
    }

    public Double getReturnOnInvestment() {
        return returnOnInvestment;
    }

    public Double getQuickRatio() {
        return quickRatio;
    }

    public Double getCurrentRatio() {
        return currentRatio;
    }

    public Double getInterestCoverage() {
        return interestCoverage;
    }

    public Double getTotalDebtToCapital() {
        return totalDebtToCapital;
    }

    public Double getLtDebtToEquity() {
        return ltDebtToEquity;
    }

    public Double getTotalDebtToEquity() {
        return totalDebtToEquity;
    }

    public Double getEpsTTM() {
        return epsTTM;
    }

    public Double getEpsChangePercentTTM() {
        return epsChangePercentTTM;
    }

    public Double getEpsChangeYear() {
        return epsChangeYear;
    }

    public Double getEpsChange() {
        return epsChange;
    }

    public Double getRevChangeYear() {
        return revChangeYear;
    }

    public Double getRevChangeTTM() {
        return revChangeTTM;
    }

    public Double getRevChangeIn() {
        return revChangeIn;
    }

    public Double getSharesOutstanding() {
        return sharesOutstanding;
    }

    public Double getMarketCapFloat() {
        return marketCapFloat;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public Double getBookValuePerShare() {
        return bookValuePerShare;
    }

    public Double getShortIntToFloat() {
        return shortIntToFloat;
    }

    public Double getShortIntDayToCover() {
        return shortIntDayToCover;
    }

    public Double getDivGrowthRate3Year() {
        return divGrowthRate3Year;
    }

    public Double getDividendPayAmount() {
        return dividendPayAmount;
    }

    public String getDividendPayDate() {
        return dividendPayDate;
    }

    public Double getBeta() {
        return beta;
    }

    public Double getVol1DayAvg() {
        return vol1DayAvg;
    }

    public Double getVol10DayAvg() {
        return vol10DayAvg;
    }

    public Double getVol3MonthAvg() {
        return vol3MonthAvg;
    }

}
