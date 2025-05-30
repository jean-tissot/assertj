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
package org.assertj.tests.core.api.optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.OptionalShouldBePresent.shouldBePresent;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.tests.core.testkit.ErrorMessagesForTest.shouldBeEqualMessage;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import java.util.Optional;
import java.util.function.Consumer;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class OptionalAssert_hasValueSatisfying_Test {

  public static final Consumer<String> NO_OP = s -> {};

  @Test
  void should_fail_when_optional_is_null() {
    // GIVEN
    @SuppressWarnings("OptionalAssignedToNull")
    Optional<String> nullActual = null;
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(nullActual).hasValueSatisfying(NO_OP));
    // THEN
    then(error).hasMessage(actualIsNull());
  }

  @Test
  void should_fail_when_optional_is_empty() {
    // GIVEN
    Optional<String> actual = Optional.empty();
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(actual).hasValueSatisfying(NO_OP));
    // THEN
    then(error).hasMessage(shouldBePresent(Optional.empty()).create());
  }

  @Test
  void should_pass_when_consumer_passes() {
    assertThat(Optional.of("something")).hasValueSatisfying(s -> assertThat(s).isEqualTo("something")
                                                                              .startsWith("some")
                                                                              .endsWith("thing"));
    assertThat(Optional.of(10)).hasValueSatisfying(i -> assertThat(i).isGreaterThan(9));
  }

  @Test
  void should_fail_from_consumer() {
    // GIVEN
    ThrowingCallable code = () -> assertThat(Optional.of("something else")).hasValueSatisfying(s -> assertThat(s).isEqualTo("something"));
    // WHEN
    AssertionError assertionError = expectAssertionError(code);
    // THEN
    then(assertionError).hasMessage(shouldBeEqualMessage("\"something else\"", "\"something\""));
  }
}
