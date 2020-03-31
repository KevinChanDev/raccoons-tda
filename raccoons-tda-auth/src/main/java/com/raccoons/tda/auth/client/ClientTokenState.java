package com.raccoons.tda.auth.client;

import java.util.concurrent.atomic.AtomicLong;

public class ClientTokenState {

    private String owner;
    private AtomicLong lastMessageTime;
    private AtomicLong refreshTime;
    private AtomicLong expirationTime;

    private void temp(){

    }

}
