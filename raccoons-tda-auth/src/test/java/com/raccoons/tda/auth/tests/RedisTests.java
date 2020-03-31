package com.raccoons.tda.auth.tests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.raccoons.tda.auth.service.data.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(MockitoJUnitRunner.class)
public class RedisTests {

    @Mock
    private RedisService redisService;

    @Test
    public void setValueGetValue() throws ExecutionException, InterruptedException {
        final String testKey1 = "testKey1";
        final String testKey2 = "testKey2";

        final String testValue1 = "testValue1";
        final String testValue2 = "testValue2";

        when(redisService.setValue(testKey1, testValue1)).thenReturn(CompletableFuture.completedFuture("OK"));
        when(redisService.setValue(testKey2, testValue2)).thenReturn(CompletableFuture.completedFuture("OK"));

        when(redisService.getValue(testKey1)).thenReturn(CompletableFuture.completedFuture(testValue1));
        when(redisService.getValue(testKey2)).thenReturn(CompletableFuture.completedFuture(testValue2));

        final CompletableFuture<String> result1 = redisService.setValue(testKey1, testValue1)
                .thenCompose(s -> redisService.getValue(testKey1));

        final CompletableFuture<String> result2 = redisService.setValue(testKey2, testValue2)
                .thenCompose(s -> redisService.getValue(testKey2));

        assertEquals(testValue1, result1.get());
        assertEquals(testValue2, result2.get());
    }

    //@Test
    public void delValue() throws ExecutionException, InterruptedException {
        final String testKey3 = "testKey3";
        final String testValue3 = "testValue3";

        final CompletableFuture<String> result3 = redisService.setValue(testKey3, testValue3)
                .thenCompose(s -> redisService.deleteValue(testKey3))
                .thenCompose(s -> redisService.getValue(testKey3));

        assertNull(result3.get());
    }

}
