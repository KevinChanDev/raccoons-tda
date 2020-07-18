package com.raccoons.tda.api.model.order;

public class FixedIncome extends OrderInstrument {

    public String getMaturityDate() {
        return (String) values.get(MATURITY_DATE);
    }

    public Double getVariableRate() {
        return (Double) values.get(VARIABLE_RATE);
    }

    public Double getFactor(){
        return (Double) values.get(FACTOR);
    }

}
