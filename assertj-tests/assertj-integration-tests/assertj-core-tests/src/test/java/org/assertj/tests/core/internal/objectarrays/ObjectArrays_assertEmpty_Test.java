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
package org.assertj.tests.core.internal.objectarrays;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.tests.core.testkit.ObjectArrays.emptyArray;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import org.junit.jupiter.api.Test;

/**
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class ObjectArrays_assertEmpty_Test extends ObjectArraysBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    // WHEN
    AssertionError error = expectAssertionError(() -> arrays.assertEmpty(INFO, null));
    // THEN
    then(error).hasMessage(actualIsNull());
  }

  @Test
  void should_fail_if_actual_is_not_empty() {
    // GIVEN
    Character[] actual = { 'a', 'b' };
    // WHEN
    AssertionError error = expectAssertionError(() -> arrays.assertEmpty(INFO, actual));
    // THEN
    then(error).hasMessage(shouldBeEmpty(actual).create());
  }

  @Test
  void should_pass_if_actual_is_empty() {
    arrays.assertEmpty(INFO, emptyArray());
  }
}
