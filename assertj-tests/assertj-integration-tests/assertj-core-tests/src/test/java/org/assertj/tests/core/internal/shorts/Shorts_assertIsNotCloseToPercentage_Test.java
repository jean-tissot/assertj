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
package org.assertj.tests.core.internal.shorts;

import static java.lang.Math.abs;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.assertj.core.data.Percentage.withPercentage;
import static org.assertj.core.error.ShouldNotBeEqualWithinPercentage.shouldNotBeEqualWithinPercentage;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.tests.core.testkit.TestData.someInfo;
import static org.assertj.tests.core.util.AssertionsUtil.assertThatAssertionErrorIsThrownBy;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Shorts_assertIsNotCloseToPercentage_Test extends ShortsBaseTest {

  private static final Short ZERO = (short) 0;
  private static final Short ONE = (short) 1;
  private static final Short TEN = (short) 10;
  private static final Short ONE_HUNDRED = (short) 100;

  @Test
  void should_fail_if_actual_is_null() {
    assertThatAssertionErrorIsThrownBy(() -> shorts.assertIsNotCloseToPercentage(someInfo(), null, ONE,
                                                                                 withPercentage(ONE))).withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_expected_value_is_null() {
    assertThatNullPointerException().isThrownBy(() -> shorts.assertIsNotCloseToPercentage(someInfo(), ONE, null,
                                                                                          withPercentage(ONE)));
  }

  @Test
  void should_fail_if_percentage_is_null() {
    assertThatNullPointerException().isThrownBy(() -> shorts.assertIsNotCloseToPercentage(someInfo(), ONE, ZERO, null));
  }

  @Test
  void should_fail_if_percentage_is_negative() {
    assertThatIllegalArgumentException().isThrownBy(() -> shorts.assertIsNotCloseToPercentage(someInfo(), ONE, ZERO,
                                                                                              withPercentage((short) -1)));
  }

  @ParameterizedTest
  @CsvSource({
      "1, 2, 1",
      "1, 11, 90",
      "-1, -2, 1",
      "-1, -11, 90",
      "0, -1, 99"
  })
  void should_pass_if_difference_is_greater_than_given_percentage(Short actual, Short other, Short percentage) {
    shorts.assertIsNotCloseToPercentage(someInfo(), actual, other, withPercentage(percentage));
  }

  @ParameterizedTest
  @CsvSource({
      "1, 1, 0",
      "2, 1, 100",
      "1, 2, 50",
      "-1, -1, 0",
      "-2, -1, 100",
      "-1, -2, 50"
  })
  void should_fail_if_difference_is_equal_to_given_percentage(Short actual, Short other, Short percentage) {
    // GIVEN
    AssertionInfo info = someInfo();
    // WHEN
    expectAssertionError(() -> shorts.assertIsNotCloseToPercentage(someInfo(), actual, other, withPercentage(percentage)));
    // THEN
    verify(failures).failure(info,
                             shouldNotBeEqualWithinPercentage(actual, other, withPercentage(percentage), abs(actual - other)));
  }

  @Test
  void should_fail_if_actual_is_too_close_to_expected_value() {
    // GIVEN
    AssertionInfo info = someInfo();
    // WHEN
    expectAssertionError(() -> shorts.assertIsNotCloseToPercentage(someInfo(), ONE, TEN, withPercentage(ONE_HUNDRED)));
    // THEN
    verify(failures).failure(info, shouldNotBeEqualWithinPercentage(ONE, TEN, withinPercentage(100), TEN - ONE));
  }
}
