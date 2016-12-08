/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.warlock.flume.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.flume.Event;
import org.apache.log4j.Logger;

import cn.com.warlock.flume.source.PeriodicEmissionSource;
import cn.com.warlock.flume.tools.RollingCounters;

import com.google.common.collect.Lists;

public abstract class RollingCountInterceptor<T> implements AnalyticInterceptor {
  private static final Logger LOG = Logger.getLogger(RollingCountInterceptor.class);
  public static final String NUM_BUCKETS = "numBuckets";
  public static final String WINDOW_LEN_SEC = "windowLenSec";
  private final RollingCounters<T> counters;

  public RollingCountInterceptor(int numBuckets, int windowLenSec) {
    this.counters = new RollingCounters<T>(numBuckets, windowLenSec);
    LOG.info(String.format("Initialising RollingCountInterceptor: buckets=%d, "
        + "window length=%d sec", numBuckets, windowLenSec));
  }

  /** {@inheritDoc} */
  @Override
  public void initialize() {
    // no-op
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    // no-op
  }

  /** {@inheritDoc} */
  @Override
  public Event intercept(Event event) {
    List<T> objects = getObjectsToCount(event);
    if (LOG.isDebugEnabled()) {
      LOG.debug(String.format("Identified %d objects to count from event: %s", objects.size(),
        objects));
    }

    if (!objects.isEmpty()) {
      Map<String, String> headers = event.getHeaders();
      for (T obj : objects) {
        long total = counters.incrementCount(obj);
        headers.put(String.valueOf(obj), String.valueOf(total));
        if (LOG.isDebugEnabled()) {
          LOG.debug(String.format("Added counter to event header: %s=%d", obj.toString(), total));
        }
      }
    }
    return event;
  }

  /** {@inheritDoc} */
  @Override
  public List<Event> intercept(List<Event> events) {
    List<Event> intercepted = Lists.newArrayListWithCapacity(events.size());
    for (Event e : events) {
      intercepted.add(intercept(e));
    }
    return intercepted;
  }

  /**
   * Gets the current objects and their totals
   * @return Map of objects to totals
   */
  public Map<T, Long> getCounters() {
    return counters.getCounters();
  }

  /**
   * Extracts the objects to count from the given event
   * @param event
   * @return The objects to count
   */
  public abstract List<T> getObjectsToCount(Event event);
}
