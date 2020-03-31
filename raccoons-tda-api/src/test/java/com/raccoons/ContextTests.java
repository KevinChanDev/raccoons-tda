package com.raccoons;

import com.raccoons.tda.context.*;
import com.raccoons.tda.logging.EmptyTDAInstructionLogger;
import com.raccoons.tda.logging.EmptyTDALogger;
import com.raccoons.tda.net.AsyncTDAHttpClient;
import com.raccoons.tda.net.AsyncTDAWebSocketClient;
import com.raccoons.tda.net.TDAHttpClient;
import com.raccoons.tda.net.TDAWebSocketClient;
import org.junit.Test;

public class ContextTests {

    @Test
    public void initializingContexts() {
        final TDALoggerContext tdaLoggerContext = BaseTDALoggerContext.newInstance(EmptyTDALogger.newInstance(),
                new EmptyTDAInstructionLogger());

        final TDAHttpClient tdaHttpClient = new AsyncTDAHttpClient(tdaLoggerContext);
        final TDAWebSocketClient tdaWebSocketClient = new AsyncTDAWebSocketClient();
        final BaseTDAClientProvider baseTDAClientContext = BaseTDAClientProvider.newInstance(tdaHttpClient, tdaWebSocketClient);

        final TDAContext tdaContext = TDAContextBuilder.newBuilder()
                .clientProvider(baseTDAClientContext)
                .loggerContext(tdaLoggerContext)
                .build();

    }
}
