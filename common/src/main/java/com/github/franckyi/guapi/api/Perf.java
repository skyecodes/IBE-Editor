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
    private static final Map<String, StopWatch> watches = new HashMap<>();

    public static void start(String name) {
        watches.put(name, StopWatch.createStarted());
    }

    public static void end(String name) {
        StopWatch watch = watches.get(name);
        if (watch != null) {
            watch.stop();
            logger.debug("{}: {} ms", name, watch.getTime(TimeUnit.MICROSECONDS) / 1000.);
        }
    }
}
