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
package org.assertj.core.api.list;

import static org.assertj.core.testkit.AlwaysEqualComparator.alwaysEqual;
import static org.mockito.Mockito.verify;

import java.util.Comparator;

import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ListAssertBaseTest;

/**
 * Tests for <code>{@link AbstractIterableAssert#isSortedAccordingTo(Comparator)}</code>.
 *
 * @author Joel Costigliola
 */
class ListAssert_isSortedAccordingToComparator_Test extends ListAssertBaseTest {

  private Comparator<String> comparator = alwaysEqual();

  @Override
  protected ListAssert<String> invoke_api_method() {
    return assertions.isSortedAccordingTo(comparator);
  }

  @Override
  protected void verify_internal_effects() {
    verify(lists).assertIsSortedAccordingToComparator(getInfo(assertions), getActual(assertions), comparator);
  }
}
