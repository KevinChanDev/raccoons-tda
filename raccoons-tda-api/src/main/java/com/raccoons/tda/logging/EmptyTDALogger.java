package com.raccoons.tda.logging;

public class EmptyTDALogger implements TDALogger {

    public static EmptyTDALogger newInstance() {
        return new EmptyTDALogger();
    }

    @Override
    public final void log(int level, CharSequence message) {
    }

    @Override
    public final void trace(CharSequence message) {
    }

    @Override
    public final void debug(CharSequence message) {
    }

    @Override
    public final void info(CharSequence message) {
    }

    @Override
    public final void warn(CharSequence message) {
    }

    @Override
    public final void error(CharSequence message) {
    }

    @Override
    public final void error(CharSequence message, Throwable throwable) {
    }

    @Override
    public final void fatal(CharSequence message) {
    }

    @Override
    public final boolean isLevelEnabled(int level) {
        return false;
    }

    @Override
    public final boolean isTraceEnabled() {
        return false;
    }

    @Override
    public final boolean isDebugEnabled() {
        return false;
    }

    @Override
    public final boolean isInfoEnabled() {
        return false;
    }

    @Override
    public final boolean isWarnEnabled() {
        return false;
    }

    @Override
    public final boolean isErrorEnabled() {
        return false;
    }

    @Override
    public final boolean isFatalEnabled() {
        return false;
    }
}
