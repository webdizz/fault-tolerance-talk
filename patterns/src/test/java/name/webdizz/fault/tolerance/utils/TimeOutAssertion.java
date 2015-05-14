package name.webdizz.fault.tolerance.utils;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;

public class TimeOutAssertion {
    public static final int NANOS_IN_MS = 1000000;

    public static void assertTheCallTookBetween(
            long startNanos, int atLeastMillis, int atMostMillis) {
        long delta = System.nanoTime() - startNanos;
        assertThat(delta >= atLeastMillis * NANOS_IN_MS, Is.is(true));
        assertThat(delta <= atMostMillis * NANOS_IN_MS, Is.is(true));
    }
}