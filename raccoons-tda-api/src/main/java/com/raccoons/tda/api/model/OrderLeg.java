package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.value.Instruction;
import com.raccoons.tda.api.value.OrderLegType;
import com.raccoons.tda.api.value.PositionEffect;

public class OrderLeg {

    @JsonProperty("orderLegType")
    private OrderLegType orderLegType;

    @JsonProperty("instrument")
    private Instrument instrument;

    @JsonProperty("quantity")
    private Double quantity;

    @JsonProperty("instruction")
    private Instruction instruction;

    @JsonProperty("legId")
    private Integer legId;

    @JsonProperty("positionEffect")
    private PositionEffect positionEffect;

    public OrderLegType getOrderLegType() {
        return orderLegType;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public Integer getLegId() {
        return legId;
    }

    public PositionEffect getPositionEffect() {
        return positionEffect;
    }
}
