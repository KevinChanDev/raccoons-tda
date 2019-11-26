package com.raccoons.tda.api.request.builder;

import com.raccoons.tda.api.request.OrderPositionRequest;
import com.raccoons.tda.api.value.Status;
import com.raccoons.tda.util.SimpleDate;

public class OrderPositionRequests {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz";

    public static OrderPositionRequest get(Status status) {
        return new OrderPositionRequest(null, 0, null, null, status);
    }

    public static OrderPositionRequest daysBeforeToday(int days) {
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String accountId;
        private int maxResults = 1000;
        private String toEnteredTime;
        private String fromEnteredTime;
        private Status status;

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder maxResults(int maxResults) {
            this.maxResults = maxResults;
            return this;
        }

        public Builder fromEnteredTime(SimpleDate fromEnteredTime) {
            this.fromEnteredTime = String.format("%04d-%02d-%02d", fromEnteredTime.getYear(),
                    fromEnteredTime.getMonth(), fromEnteredTime.getDayOfMonth());
            return this;
        }

        public Builder toEnteredTime(SimpleDate toEnteredTime) {
            this.toEnteredTime = String.format("%04d-%02d-%02d", toEnteredTime.getYear(),
                    toEnteredTime.getMonth(), toEnteredTime.getDayOfMonth());
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public OrderPositionRequest build() {
            return new OrderPositionRequest(accountId, maxResults, fromEnteredTime, toEnteredTime, status);
        }
    }
}
