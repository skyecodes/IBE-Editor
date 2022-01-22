package com.github.franckyi.guapi.api;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Deprecated
public final class Perf {
    private static final Logger logger = LogManager.getLogger();
    private static final Map<Object, StopWatch> watches = new HashMap<>();

    public static void start(Object key) {
        watches.put(key, StopWatch.createStarted());
    }

    public static void end(Object key, String s) {
        StopWatch watch = watches.get(key);
        if (watch != null) {
            watch.stop();
            logger.debug("{} {}: {} ms", key, s, watch.getTime(TimeUnit.MICROSECONDS) / 1000.);
        }
    }
}
