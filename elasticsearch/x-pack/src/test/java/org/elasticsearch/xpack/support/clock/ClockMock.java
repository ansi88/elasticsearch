/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.support.clock;

import org.elasticsearch.common.unit.TimeValue;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.concurrent.TimeUnit;

/**
 * A clock that can be modified for testing.
 */
public class ClockMock implements Clock {

    private DateTime now = DateTime.now(DateTimeZone.UTC);

    @Override
    public long millis() {
        return now.getMillis();
    }

    @Override
    public long nanos() {
        return TimeUnit.MILLISECONDS.toNanos(now.getMillis());
    }

    @Override
    public DateTime nowUTC() {
        return now(DateTimeZone.UTC);
    }

    @Override
    public DateTime now(DateTimeZone timeZone) {
        return now.toDateTime(timeZone);
    }

    @Override
    public TimeValue timeElapsedSince(DateTime time) {
        return TimeValue.timeValueMillis(now.getMillis() - time.getMillis());
    }

    public ClockMock setTime(DateTime now) {
        this.now = now;
        return this;
    }

    public ClockMock fastForward(TimeValue timeValue) {
        return setTime(now.plusMillis((int) timeValue.millis()));
    }

    public ClockMock fastForwardSeconds(int seconds) {
        return fastForward(TimeValue.timeValueSeconds(seconds));
    }

    public ClockMock rewind(TimeValue timeValue) {
        return setTime(now.minusMillis((int) timeValue.millis()));
    }

    public ClockMock rewindSeconds(int seconds) {
        return rewind(TimeValue.timeValueSeconds(seconds));
    }
}
