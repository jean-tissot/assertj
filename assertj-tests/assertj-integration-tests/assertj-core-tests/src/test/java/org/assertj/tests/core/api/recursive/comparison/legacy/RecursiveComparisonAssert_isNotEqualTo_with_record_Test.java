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
package org.assertj.tests.core.api.recursive.comparison.legacy;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class RecursiveComparisonAssert_isNotEqualTo_with_record_Test extends WithLegacyIntrospectionStrategyBaseTest {

  // https://github.com/assertj/assertj/issues/3539
  @Test
  void should_compare_records_with_component_named_as_boolean_accessor() {
    // GIVEN
    record Record(String field, boolean isField) {
    }
    Record actual = new Record("value1", true);
    Record other = new Record("value2", true);
    // WHEN/THEN
    then(actual).usingRecursiveComparison(recursiveComparisonConfiguration).isNotEqualTo(other);
  }

}
