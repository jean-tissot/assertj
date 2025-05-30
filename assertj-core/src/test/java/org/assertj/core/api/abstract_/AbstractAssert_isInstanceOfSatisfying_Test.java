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
package org.assertj.core.api.abstract_;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssertBaseTest;
import org.assertj.core.api.ConcreteAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.testkit.Jedi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractAssert_isInstanceOfSatisfying_Test extends AbstractAssertBaseTest {

  // init here to make it available in create_assertions()
  private final Jedi yoda = new Jedi("Yoda", "Green");
  private final Jedi luke = new Jedi("Luke Skywalker", "Green");
  private Consumer<Jedi> jediRequirements;

  @BeforeEach
  void setup() {
    jediRequirements = jedi -> {
      assertThat(jedi.lightSaberColor).as("check light saber").isEqualTo("Green");
      assertThat(jedi.getName()).as("check name").doesNotContain("Dark");
    };
  }

  @Override
  protected ConcreteAssert create_assertions() {
    return new ConcreteAssert(yoda);
  }

  @Override
  protected ConcreteAssert invoke_api_method() {
    return assertions.isInstanceOfSatisfying(Jedi.class, jediRequirements);
  }

  @Override
  protected void verify_internal_effects() {
    verify(objects).assertIsInstanceOf(getInfo(assertions), getActual(assertions), Jedi.class);
  }

  @Test
  void should_satisfy_single_requirement() {
    assertThat(yoda).isInstanceOfSatisfying(Jedi.class, jedi -> assertThat(jedi.lightSaberColor).isEqualTo("Green"));
  }

  @Test
  void should_satisfy_multiple_requirements() {
    assertThat(yoda).isInstanceOfSatisfying(Jedi.class, jediRequirements);
    assertThat(luke).isInstanceOfSatisfying(Jedi.class, jediRequirements);
  }

  @Test
  void should_fail_according_to_requirements() {
    // GIVEN
    ThrowingCallable code = () -> assertThat(new Jedi("Vader", "Red")).isInstanceOfSatisfying(Jedi.class, jediRequirements);
    // WHEN
    AssertionError assertionError = expectAssertionError(code);
    // THEN
    then(assertionError).hasMessage(format("[check light saber] %n" +
                                           "expected: \"Green\"%n" +
                                           " but was: \"Red\""));
  }

  @Test
  void should_fail_if_consumer_is_null() {
    assertThatNullPointerException().isThrownBy(() -> assertThat(yoda).isInstanceOfSatisfying(Jedi.class, null))
                                    .withMessage("The Consumer<T> expressing the assertions requirements must not be null");
  }

  @Test
  void should_fail_if_type_is_null() {
    assertThatNullPointerException().isThrownBy(() -> assertThat(yoda).isInstanceOfSatisfying(null, jediRequirements))
                                    .withMessage("The given type should not be null");
  }
}
