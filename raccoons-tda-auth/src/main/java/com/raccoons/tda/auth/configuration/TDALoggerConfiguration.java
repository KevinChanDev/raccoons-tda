package com.raccoons.tda.auth.configuration;

import com.raccoons.tda.auth.service.AuthClientService;
import com.raccoons.tda.logging.TDAInstructionLogger;
import com.raccoons.tda.logging.TDALogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class TDALoggerConfiguration {

    private static final Logger logger = LogManager.getLogger(AuthClientService.class);

    @Bean
    public TDALogger tdaLogger() {
        return new TDALogger() {
            @Override
            public void log(int level, CharSequence message) {
            }

            @Override
            public void trace(CharSequence message) {
                logger.trace(message);
            }

            @Override
            public void debug(CharSequence message) {
                logger.debug(message);

            }

            @Override
            public void info(CharSequence message) {
                logger.info(message);
            }

            @Override
            public void warn(CharSequence message) {
                logger.warn(message);
            }

            @Override
            public void error(CharSequence message) {
                logger.error(message);
            }

            @Override
            public void error(CharSequence message, Throwable throwable) {
                logger.error(message, throwable);
            }

            @Override
            public void fatal(CharSequence message) {
                logger.fatal(message);
            }

            @Override
            public boolean isLevelEnabled(int level) {
                return false;
            }

            @Override
            public boolean isTraceEnabled() {
                return logger.isTraceEnabled();
            }

            @Override
            public boolean isDebugEnabled() {
                return logger.isDebugEnabled();
            }

            @Override
            public boolean isInfoEnabled() {
                return logger.isInfoEnabled();
            }

            @Override
            public boolean isWarnEnabled() {
                return logger.isWarnEnabled();
            }

            @Override
            public boolean isErrorEnabled() {
                return logger.isErrorEnabled();
            }

            @Override
            public boolean isFatalEnabled() {
                return logger.isFatalEnabled();
            }
        };
    }

    @Bean
    public TDAInstructionLogger tdaInstructionLogger() {
        return new TDAInstructionLogger() {
            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void log(Map<String, Object> values) {
                logger.info("Logging instruction: " + values);
            }
        };
    }
}
