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
package org.assertj.core.internal.floatarrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.testkit.FloatArrays.arrayOf;
import static org.assertj.core.testkit.FloatArrays.emptyArray;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.FloatArrays;
import org.assertj.core.internal.FloatArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link FloatArrays#assertNotEmpty(AssertionInfo, float[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class FloatArrays_assertNotEmpty_Test extends FloatArraysBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertNotEmpty(someInfo(), null))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_actual_is_empty() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> arrays.assertNotEmpty(info, emptyArray()));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotBeEmpty());
  }

  @Test
  void should_pass_if_actual_is_not_empty() {
    arrays.assertNotEmpty(someInfo(), arrayOf(8f));
  }
}
