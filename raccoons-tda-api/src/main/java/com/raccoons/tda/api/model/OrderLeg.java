package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.Instruction;
import com.raccoons.tda.api.value.OrderLegType;
import com.raccoons.tda.api.value.PositionEffect;


public class OrderLeg {

    private OrderLegType orderLegType;
    private Instrument instrument;
    private Double quantity;
    private Instruction instruction;
    private Integer legId;
    private PositionEffect positionEffect;

}
