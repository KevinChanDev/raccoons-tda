package com.raccoons.tda.logging;

import java.io.PrintWriter;

public class AsyncTDALogger implements TDALogger {

    private final int level;
    private final PrintWriter printWriter;
    private final PrintWriter printWriterError;

    public AsyncTDALogger(int level) {
        this.level = Math.max(level, 0);
        this.printWriter = new PrintWriter(System.out);
        this.printWriterError = new PrintWriter(System.err);
    }

    @Override
    public void log(int level, CharSequence message) {
        if (isLevelEnabled(level)) {

        }
    }

    @Override
    public void trace(CharSequence message) {
        if (isTraceEnabled()) {

        }
    }

    @Override
    public void debug(CharSequence message) {
        if (isDebugEnabled()) {

        }
    }

    @Override
    public void info(CharSequence message) {
        if (isInfoEnabled()) {

        }
    }

    @Override
    public void warn(CharSequence message) {
        if (isWarnEnabled()) {

        }
    }

    @Override
    public void error(CharSequence message) {
        if (isErrorEnabled()) {

        }
    }

    @Override
    public void error(CharSequence message, Throwable throwable) {
        if (isErrorEnabled()) {

        }
    }

    @Override
    public void fatal(CharSequence message) {
        if (isFatalEnabled()) {

        }
    }

    @Override
    public boolean isLevelEnabled(int level) {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

}
