package com.raccoons.tda.auth.configuration;

import com.raccoons.tda.api.client.TDAClient;
import com.raccoons.tda.context.*;
import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;
import com.raccoons.tda.net.AsyncTDAHttpClient;
import com.raccoons.tda.net.TDAHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TDAContextConfiguration {

    @Autowired
    private TDALogger tdaLogger;

    @Autowired
    private TDAInstructionLogger tdaInstructionLogger;

    @Bean
    public TDALoggerContext tdaLoggerContext() {
        return BaseTDALoggerContext.newInstance(tdaLogger, tdaInstructionLogger);
    }

    @Bean
    public TDAHttpClient tdaHttpClient() {
        return new AsyncTDAHttpClient(tdaLoggerContext());
    }

    @Bean
    public TDAClientProvider tdaClientProvider() {
        return BaseTDAClientProvider.newInstance(tdaHttpClient(), null);
    }

    @Bean
    public TDAContext tdaContext() {
        final TDALoggerContext tdaLoggerContext = tdaLoggerContext();
        final TDAClientProvider tdaClientProvider = tdaClientProvider();

        return TDAContextBuilder.newBuilder()
                .loggerContext(tdaLoggerContext)
                .clientProvider(tdaClientProvider)
                .build();
    }

    @Bean
    public TDAClient tdaClient() {
        final TDAContext tdaContext = tdaContext();
        return new TDAClient(tdaContext);
    }

}
