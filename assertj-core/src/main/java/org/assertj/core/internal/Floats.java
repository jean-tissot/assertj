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
package org.assertj.core.internal;

import org.assertj.core.api.comparisonstrategy.ComparisonStrategy;
import org.assertj.core.api.comparisonstrategy.StandardComparisonStrategy;

import static java.lang.Math.abs;

/**
 * Reusable assertions for <code>{@link Float}</code>s.
 *
 * @author Drummond Dawson
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 */
public class Floats extends RealNumbers<Float> {

  private static final Floats INSTANCE = new Floats();

  /**
   * Returns the singleton instance of this class based on {@link StandardComparisonStrategy}.
   *
   * @return the singleton instance of this class based on {@link StandardComparisonStrategy}.
   */
  public static Floats instance() {
    return INSTANCE;
  }

  // TODO reduce the visibility of the fields annotated with @VisibleForTesting
  Floats() {
    super();
  }

  public Floats(ComparisonStrategy comparisonStrategy) {
    super(comparisonStrategy);
  }

  @Override
  protected Float zero() {
    return 0.0f;
  }

  @Override
  protected Float one() {
    return 1.0f;
  }

  @Override
  protected Float NaN() {
    return Float.NaN;
  }

  @Override
  protected Float absDiff(Float actual, Float other) {
    return isNanOrInfinite(actual) || isNanOrInfinite(other)
        ? abs(actual - other)
        : abs(absBigDecimalDiff(actual, other).floatValue());
  }

  @Override
  protected boolean isFinite(Float value) {
    return Float.isFinite(value);
  }

  @Override
  protected boolean isNotFinite(Float value) {
    return !Float.isFinite(value);
  }

  @Override
  protected boolean isInfinite(Float value) {
    return Float.isInfinite(value);
  }

  @Override
  protected boolean isNotInfinite(Float value) {
    return !Float.isInfinite(value);
  }

  @Override
  protected boolean isNaN(Float value) {
    return Float.isNaN(value);
  }
}
