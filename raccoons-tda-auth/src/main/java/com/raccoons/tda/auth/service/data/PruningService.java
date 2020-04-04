package com.raccoons.tda.auth.service.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PruningService {

    private static final Logger logger = LogManager.getLogger(PruningService.class);

//    @Scheduled(cron = "0 0 * * *")
//    private void pruneExpired() {
//        logger.info("Pruning expired data entries.");
//    }

}
