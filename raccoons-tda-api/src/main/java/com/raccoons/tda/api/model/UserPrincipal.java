package com.raccoons.tda.api.model;

import java.util.List;
import java.util.Map;

public class UserPrincipal {

    private String authToken;
    private String userId;
    private String userCdDomainId;
    private String primaryAccountId;
    private String lastLoginTime;
    private String tokenExpirationTime;
    private String loginTime;
    private String accessLevel;
    private boolean stalePassword;
    private String professionalStatus;
    private StreamerSubscriptionKeys streamerSubscriptionKeys;
    private Quotes quotes;
    private List<Account> accounts;
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

        private String streamerBinaryUrl;
        private String streamerSocketUrl;
        private String token;
        private String tokenTimestamp;
        private String userGroup;
        private String accessLevel;
        private String acl;
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

        private boolean isNyseDelayed;
        private boolean isNasdaqDelayed;
        private boolean isOpraDelayed;
        private boolean isAmexDelayed;
        private boolean isCmeDelayed;
        private boolean isIceDelayed;
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

        private String accountId;
        private String displayName;
        private String accountCdDomainId;
        private String company;
        private String segment;
        private String acl;
        private Authorizations authorizations;
        private Preferences preferences;
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

            private boolean apex;
            private boolean levelTwoQuotes;
            private boolean stockTrading;
            private boolean marginTrading;
            private boolean streamingNews;
            private boolean streamerAccess;
            private boolean advancedMargin;
            private boolean scottradeAccount;
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

            private boolean expressTrading;
            private boolean directOptionsRouting;
            private boolean directEquityRouting;
            private String defaultEquityOrderLegInstruction;
            private String defaultEquityOrderType;
            private String defaultEquityOrderPriceLinkType;
            private String defaultEquityOrderDuration;
            private String defaultEquityOrderMarketSession;
            private int defaultEquityQuantity;
            private String mutualFundTaxLotMethod;
            private String optionTaxLotMethod;
            private String equityTaxLotMethod;
            private String defaultAdvancedToolLaunch;
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
