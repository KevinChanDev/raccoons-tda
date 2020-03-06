package com.raccoons.tda.logging;

public interface TDALogger {

    void log(int level, CharSequence message);

    void trace(CharSequence message);
    void debug(CharSequence message);
    void info(CharSequence message);
    void warn(CharSequence message);
    void error(CharSequence message);
    void error(CharSequence message, Throwable throwable);
    void fatal(CharSequence message);

    boolean isLevelEnabled(int level);
    boolean isTraceEnabled();
    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isWarnEnabled();
    boolean isErrorEnabled();
    boolean isFatalEnabled();

}
