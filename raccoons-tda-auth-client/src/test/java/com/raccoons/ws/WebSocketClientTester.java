package com.raccoons.ws;

import com.raccoons.tda.auth.client.RaccoonsTDAAuthClient;
import org.junit.Assert;
import org.junit.Test;

public class WebSocketClientTester {

    @Test
    public void testAccessToken() throws InterruptedException {
        final String ws = "ws://localhost:8080/connect";
        final String owner = "kevinwchan2";

        final RaccoonsTDAAuthClient authClient = new RaccoonsTDAAuthClient(ws);

        final String ac = authClient.getAccessToken(owner);
        Thread.sleep(3500);
        System.out.println(ac);
        authClient.refreshAccessToken(owner);
        Thread.sleep(3500);

        //System.out.println(authClient.getAccessToken(owner));
        Thread.sleep(17000);

        Assert.assertEquals(1L, 1L);
    }

}
