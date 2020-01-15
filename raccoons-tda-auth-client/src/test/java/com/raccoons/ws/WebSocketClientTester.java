package com.raccoons.ws;

import com.raccoons.tda.auth.client.RaccoonsTDAAuthClient;
import org.junit.Test;

public class WebSocketClientTester {

    @Test
    public void testConnection() throws InterruptedException {
        final String ws = "ws://localhost:8080/connection";
        final RaccoonsTDAAuthClient authClient = new RaccoonsTDAAuthClient(ws);

        for (int i = 0; i < 100; i++) {
            authClient.getAccessToken("hello!!!");
            Thread.sleep(10000);
        }

        //Assert.assertEquals(1.0, authClient.availability(), 0);
        System.out.println("Sleeping for some time!");
        Thread.sleep(100000);
    }

}
