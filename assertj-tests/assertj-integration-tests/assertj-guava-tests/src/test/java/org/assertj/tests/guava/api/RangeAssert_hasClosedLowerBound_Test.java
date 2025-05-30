/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.tests.guava.api;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.guava.api.Assertions.assertThat;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

/**
 * @author Marcin Kwaczyński
 */
public class RangeAssert_hasClosedLowerBound_Test {

  @Test
  public void should_fail_if_actual_is_null() {
    // GIVEN
    Range<Integer> actual = null;
    // WHEN
    Throwable throwable = catchThrowable(() -> assertThat(actual).hasClosedLowerBound());
    // THEN
    assertThat(throwable).isInstanceOf(AssertionError.class)
                         .hasMessage(actualIsNull());
  }

  @Test
  public void should_fail_when_range_has_opened_lower_bound() {
    // GIVEN
    final Range<Integer> actual = Range.openClosed(1, 10);
    // WHEN
    Throwable throwable = catchThrowable(() -> assertThat(actual).hasClosedLowerBound());
    // THEN
    assertThat(throwable).isInstanceOf(AssertionError.class)
                         .hasMessage(format("%nExpecting:%n" +
                                            "  (1..10]%n" +
                                            "to be closed in the lower bound but was opened"));
  }

  @Test
  public void should_pass_if_range_has_closed_lower_bound() throws Exception {
    // GIVEN
    final Range<Integer> actual = Range.closed(1, 10);
    // THEN
    assertThat(actual).hasClosedLowerBound();
  }
}
