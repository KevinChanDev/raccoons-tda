package com.raccoons.tda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class UserPrincipal {

    @JsonProperty("authToken")
    private String authToken;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userCdDomainId")
    private String userCdDomainId;

    @JsonProperty("primaryAccountId")
    private String primaryAccountId;

    @JsonProperty("lastLoginTime")
    private String lastLoginTime;

    @JsonProperty("tokenExpirationTime")
    private String tokenExpirationTime;

    @JsonProperty("loginTime")
    private String loginTime;

    @JsonProperty("accessLevel")
    private String accessLevel;

    @JsonProperty("stalePassword")
    private boolean stalePassword;

    @JsonProperty("professionalStatus")
    private String professionalStatus;

    @JsonProperty("streamerSubscriptionKeys")
    private StreamerSubscriptionKeys streamerSubscriptionKeys;

    @JsonProperty("quotes")
    private Quotes quotes;

    @JsonProperty("accounts")
    private List<Account> accounts;

    @JsonProperty("streamerInfo")
    private StreamerInfo streamerInfo;

    public String getAuthToken() {
        return authToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserCdDomainId() {
        return userCdDomainId;
    }

    public String getPrimaryAccountId() {
        return primaryAccountId;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public String getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public boolean isStalePassword() {
        return stalePassword;
    }

    public StreamerInfo getStreamerInfo() {
        return streamerInfo;
    }

    public String getProfessionalStatus() {
        return professionalStatus;
    }

    public StreamerSubscriptionKeys getStreamerSubscriptionKeys() {
        return streamerSubscriptionKeys;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public static class StreamerInfo {

        @JsonProperty("streamerBinaryUrl")
        private String streamerBinaryUrl;

        @JsonProperty("streamerSocketUrl")
        private String streamerSocketUrl;

        @JsonProperty("token")
        private String token;

        @JsonProperty("tokenTimestamp")
        private String tokenTimestamp;

        @JsonProperty("userGroup")
        private String userGroup;

        @JsonProperty("accessLevel")
        private String accessLevel;

        @JsonProperty("acl")
        private String acl;

        @JsonProperty("appId")
        private String appId;

        public String getStreamerBinaryUrl() {
            return streamerBinaryUrl;
        }

        public String getStreamerSocketUrl() {
            return streamerSocketUrl;
        }

        public String getToken() {
            return token;
        }

        public String getTokenTimestamp() {
            return tokenTimestamp;
        }

        public String getUserGroup() {
            return userGroup;
        }

        public String getAccessLevel() {
            return accessLevel;
        }

        public String getAcl() {
            return acl;
        }

        public String getAppId() {
            return appId;
        }
    }

    public static class Quotes {

        @JsonProperty("isNyseDelayed")
        private boolean isNyseDelayed;

        @JsonProperty("isNasdaqDelayed")
        private boolean isNasdaqDelayed;

        @JsonProperty("isOpraDelayed")
        private boolean isOpraDelayed;

        @JsonProperty("isAmexDelayed")
        private boolean isAmexDelayed;

        @JsonProperty("isCmeDelayed")
        private boolean isCmeDelayed;

        @JsonProperty("isIceDelayed")
        private boolean isIceDelayed;

        @JsonProperty("isForexDelayed")
        private boolean isForexDelayed;

        public boolean getIsNyseDelayed() {
            return isNyseDelayed;
        }

        public boolean getIsNasdaqDelayed() {
            return isNasdaqDelayed;
        }

        public boolean getIsOpraDelayed() {
            return isOpraDelayed;
        }

        public boolean getIsAmexDelayed() {
            return isAmexDelayed;
        }

        public boolean getIsCmeDelayed() {
            return isCmeDelayed;
        }

        public boolean getIsIceDelayed() {
            return isIceDelayed;
        }

        public boolean getIsForexDelayed() {
            return isForexDelayed;
        }

    }

    public static class StreamerSubscriptionKeys {

        @JsonProperty("keys")
        private List<Key> keys;

        public List<Key> getKeys() {
            return keys;
        }

        public static class Key {

            private String key;

            public String getKey() {
                return key;
            }
        }
    }

    public static class Account {

        @JsonProperty("accountId")
        private String accountId;

        @JsonProperty("displayName")
        private String displayName;

        @JsonProperty("accountCdDomainId")
        private String accountCdDomainId;

        @JsonProperty("company")
        private String company;

        @JsonProperty("segment")
        private String segment;

        @JsonProperty("acl")
        private String acl;

        @JsonProperty("authorizations")
        private Authorizations authorizations;

        @JsonProperty("preferences")
        private Preferences preferences;

        @JsonProperty("surrogateIds")
        private Map<String, Object> surrogateIds;

        public String getAccountId() {
            return accountId;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getAccountCdDomainId() {
            return accountCdDomainId;
        }

        public String getCompany() {
            return company;
        }

        public String getSegment() {
            return segment;
        }

        public String getAcl() {
            return acl;
        }

        public Authorizations getAuthorizations() {
            return authorizations;
        }

        public Preferences getPreferences() {
            return preferences;
        }

        public Map<String, Object> getSurrogateIds() {
            return surrogateIds;
        }

        public static class Authorizations {

            @JsonProperty("apex")
            private boolean apex;

            @JsonProperty("levelTwoQuotes")
            private boolean levelTwoQuotes;

            @JsonProperty("stockTrading")
            private boolean stockTrading;

            @JsonProperty("marginTrading")
            private boolean marginTrading;

            @JsonProperty("streamingNews")
            private boolean streamingNews;

            @JsonProperty("streamerAccess")
            private boolean streamerAccess;

            @JsonProperty("advancedMargin")
            private boolean advancedMargin;

            @JsonProperty("scottradeAccount")
            private boolean scottradeAccount;

            @JsonProperty("optionTradingLevel")
            private String optionTradingLevel;

            public boolean getApex() {
                return apex;
            }

            public boolean getLevelTwoQuotes() {
                return levelTwoQuotes;
            }

            public boolean getStockTrading() {
                return stockTrading;
            }

            public boolean getMarginTrading() {
                return marginTrading;
            }

            public boolean getStreamingNews() {
                return streamingNews;
            }

            public boolean getStreamerAccess() {
                return streamerAccess;
            }

            public boolean getAdvancedMargin() {
                return advancedMargin;
            }

            public boolean getScottradeAccount() {
                return scottradeAccount;
            }

            public String getOptionTradingLevel() {
                return optionTradingLevel;
            }
        }

        public static class Preferences {

            @JsonProperty("expressTrading")
            private boolean expressTrading;

            @JsonProperty("directOptionsRouting")
            private boolean directOptionsRouting;

            @JsonProperty("directEquityRouting")
            private boolean directEquityRouting;

            @JsonProperty("defaultEquityOrderLegInstruction")
            private String defaultEquityOrderLegInstruction;

            @JsonProperty("defaultEquityOrderType")
            private String defaultEquityOrderType;

            @JsonProperty("defaultEquityOrderPriceLinkType")
            private String defaultEquityOrderPriceLinkType;

            @JsonProperty("defaultEquityOrderDuration")
            private String defaultEquityOrderDuration;

            @JsonProperty("defaultEquityOrderMarketSession")
            private String defaultEquityOrderMarketSession;

            @JsonProperty("defaultEquityQuantity")
            private int defaultEquityQuantity;

            @JsonProperty("mutualFundTaxLotMethod")
            private String mutualFundTaxLotMethod;

            @JsonProperty("optionTaxLotMethod")
            private String optionTaxLotMethod;

            @JsonProperty("equityTaxLotMethod")
            private String equityTaxLotMethod;

            @JsonProperty("defaultAdvancedToolLaunch")
            private String defaultAdvancedToolLaunch;

            @JsonProperty("authTokenTimeout")
            private String authTokenTimeout;

            public boolean isExpressTrading() {
                return expressTrading;
            }

            public boolean isDirectOptionsRouting() {
                return directOptionsRouting;
            }

            public boolean isDirectEquityRouting() {
                return directEquityRouting;
            }

            public String getDefaultEquityOrderLegInstruction() {
                return defaultEquityOrderLegInstruction;
            }

            public String getDefaultEquityOrderType() {
                return defaultEquityOrderType;
            }

            public String getDefaultEquityOrderPriceLinkType() {
                return defaultEquityOrderPriceLinkType;
            }

            public String getDefaultEquityOrderDuration() {
                return defaultEquityOrderDuration;
            }

            public String getDefaultEquityOrderMarketSession() {
                return defaultEquityOrderMarketSession;
            }

            public int getDefaultEquityQuantity() {
                return defaultEquityQuantity;
            }

            public String getMutualFundTaxLotMethod() {
                return mutualFundTaxLotMethod;
            }

            public String getOptionTaxLotMethod() {
                return optionTaxLotMethod;
            }

            public String getEquityTaxLotMethod() {
                return equityTaxLotMethod;
            }

            public String getDefaultAdvancedToolLaunch() {
                return defaultAdvancedToolLaunch;
            }

            public String getAuthTokenTimeout() {
                return authTokenTimeout;
            }
        }
    }

}
