/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.common.util.concurrent;

import java.util.concurrent.ConcurrentMap;

public interface ConcurrentMapLong<T> extends ConcurrentMap<Long, T> {

    T get(long key);

    T remove(long key);

    T put(long key, T value);
}
