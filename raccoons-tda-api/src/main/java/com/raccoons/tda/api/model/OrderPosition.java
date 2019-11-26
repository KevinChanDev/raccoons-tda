package com.raccoons.tda.api.model;

import com.raccoons.tda.api.value.*;

import java.util.Date;
import java.util.List;

public class OrderPosition {

    private Session session;
    private Duration duration;
    private OrderType orderType;

    private CancelTime cancelTime;
    private ComplexOrderStrategy complexOrderStrategyType;
    private Double quantity;
    private Double filledQuantity;
    private Double remainingQuantity;

    private RequestedDestination requestedDestination;
    private String destinationLinkName;
    private Date releaseTime;
    private Double stopPrice;

    private StopPriceLinkBasis stopPriceLinkBasis;
    private StopPriceLinkType stopPriceLinkType;

    private StopType stopType;

    private PriceLinkBasis priceLinkBasis;
    private PriceLinkType priceLinkType;
    private Double price;

    private TaxLot taxLotMethod;

    private List<OrderLeg> orderLegCollection;
    private double activationPrice;
    private SpecialInstruction specialInstruction;
    private Long orderId;
    private Boolean cancelable;
    private Boolean editable;
    private Status status;
    private Date enteredTime;
    private Date closeTime;
    private String tag;
    private Long accountId;

    private OrderStrategyType orderStrategyType;
    private List<Void> orderActivityCollection;
    private List<Void> replacingOrderCollection;
    private List<Void> childOrderStrategies;
    private String statusDescription;

    public OrderPosition() {

    }

}
