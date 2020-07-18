package com.raccoons.tda.api.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoons.tda.api.model.ApiModel;
import com.raccoons.tda.api.model.CancelTime;
import com.raccoons.tda.api.value.*;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPosition extends ApiModel {

    @JsonProperty("session")
    private Session session;

    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("orderType")
    private OrderType orderType;

    @JsonProperty("cancelTime")
    private CancelTime cancelTime;

    @JsonProperty("complexOrderStrategyType")
    private ComplexOrderStrategy complexOrderStrategyType;

    @JsonProperty("quantity")
    private Double quantity;

    @JsonProperty("filledQuantity")
    private Double filledQuantity;

    @JsonProperty("remainingQuantity")
    private Double remainingQuantity;

    @JsonProperty("requestedDestination")
    private RequestedDestination requestedDestination;

    @JsonProperty("destinationLinkName")
    private String destinationLinkName;

    @JsonProperty("releaseTime")
    private Date releaseTime;

    @JsonProperty("stopPrice")
    private Double stopPrice;

    @JsonProperty("stopPriceLinkBasis")
    private StopPriceLinkBasis stopPriceLinkBasis;

    @JsonProperty("stopPriceLinkType")
    private StopPriceLinkType stopPriceLinkType;

    @JsonProperty("stopType")
    private StopType stopType;

    @JsonProperty("priceLinkBasis")
    private PriceLinkBasis priceLinkBasis;

    @JsonProperty("priceLinkType")
    private PriceLinkType priceLinkType;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("taxLotMethod")
    private TaxLot taxLotMethod;

    @JsonProperty("orderLegCollection")
    private List<OrderLeg> orderLegCollection;

    @JsonProperty("activationPrice")
    private Double activationPrice;

    @JsonProperty("specialInstruction")
    private SpecialInstruction specialInstruction;

    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("cancelable")
    private Boolean cancelable;

    @JsonProperty("editable")
    private Boolean editable;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("enteredTime")
    private Date enteredTime;

    @JsonProperty("closeTime")
    private Date closeTime;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("accountId")
    private Long accountId;

    @JsonProperty("orderStrategyType")
    private OrderStrategyType orderStrategyType;

    @JsonProperty("orderActivityCollection")
    private List<Void> orderActivityCollection;

    @JsonProperty("replacingOrderCollection")
    private List<Void> replacingOrderCollection;

    @JsonProperty("childOrderStrategies")
    private List<Void> childOrderStrategies;

    @JsonProperty("statusDescription")
    private String statusDescription;

    public OrderPosition() {
    }

    public Session getSession() {
        return session;
    }

    public Duration getDuration() {
        return duration;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public CancelTime getCancelTime() {
        return cancelTime;
    }

    public ComplexOrderStrategy getComplexOrderStrategyType() {
        return complexOrderStrategyType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getFilledQuantity() {
        return filledQuantity;
    }

    public Double getRemainingQuantity() {
        return remainingQuantity;
    }

    public RequestedDestination getRequestedDestination() {
        return requestedDestination;
    }

    public String getDestinationLinkName() {
        return destinationLinkName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public Double getStopPrice() {
        return stopPrice;
    }

    public StopPriceLinkBasis getStopPriceLinkBasis() {
        return stopPriceLinkBasis;
    }

    public StopPriceLinkType getStopPriceLinkType() {
        return stopPriceLinkType;
    }

    public StopType getStopType() {
        return stopType;
    }

    public PriceLinkBasis getPriceLinkBasis() {
        return priceLinkBasis;
    }

    public PriceLinkType getPriceLinkType() {
        return priceLinkType;
    }

    public Double getPrice() {
        return price;
    }

    public TaxLot getTaxLotMethod() {
        return taxLotMethod;
    }

    public List<OrderLeg> getOrderLegCollection() {
        return orderLegCollection;
    }

    public Double getActivationPrice() {
        return activationPrice;
    }

    public SpecialInstruction getSpecialInstruction() {
        return specialInstruction;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Boolean getCancelable() {
        return cancelable;
    }

    public Boolean getEditable() {
        return editable;
    }

    public Status getStatus() {
        return status;
    }

    public Date getEnteredTime() {
        return enteredTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public String getTag() {
        return tag;
    }

    public Long getAccountId() {
        return accountId;
    }

    public OrderStrategyType getOrderStrategyType() {
        return orderStrategyType;
    }

    public List<Void> getOrderActivityCollection() {
        return orderActivityCollection;
    }

    public List<Void> getReplacingOrderCollection() {
        return replacingOrderCollection;
    }

    public List<Void> getChildOrderStrategies() {
        return childOrderStrategies;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
