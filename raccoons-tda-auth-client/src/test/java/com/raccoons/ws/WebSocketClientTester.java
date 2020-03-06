package com.raccoons.ws;

import com.raccoons.tda.auth.client.RaccoonsTDAAuthClient;
import org.junit.Assert;
import org.junit.Test;

public class WebSocketClientTester {

    @Test
    public void testAccessToken() throws InterruptedException {
        final String ws = "ws://localhost:8080/connection";
        final String owner = "owner1";

        final RaccoonsTDAAuthClient authClient = new RaccoonsTDAAuthClient(ws);
        final String accessToken = authClient.getAccessToken(owner);

        System.out.println(accessToken);
        Thread.sleep(2500);
        System.out.println("token: " + authClient.getAccessToken(owner));
        Thread.sleep(2500);

        Assert.assertEquals(1L, 1L);
    }

}
