package com.raccoons.tda.api.client;

import com.raccoons.tda.context.TDAContext;
import com.raccoons.tda.context.TDAContextProvider;
import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;

import java.util.concurrent.atomic.AtomicLong;

public class BaseApiClient implements TDAContextProvider {

    private AtomicLong requestCounter;
    private TDAContext tdaContext;

    protected BaseApiClient(TDAContext tdaContext) {
        this.tdaContext = tdaContext;
        this.requestCounter = new AtomicLong();
    }

    @Override
    public TDAContext getContext() {
        return tdaContext;
    }

    public TDALogger getLogger(){
        return tdaContext.getLoggerContext().getLogger();
    }

    public TDAInstructionLogger getInstructionLogger(){
        return tdaContext.getLoggerContext().getInstructionLogger();
    }

    public long incrementRequestCounter(){
        return requestCounter.incrementAndGet();
    }
}
