/*************************************************************************
 * CONFIDENTIAL
 * __________________
 *
 * [2016] All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of {The Company} and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to {The Company}
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from {The Company}.
 */
package com.nannyband.nannyband.data.stats;

import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;

@Module public class StatsModule {
  @Provides public Stats stats() {
    return new Stats() {
      @Override public void subscribe(Callback callback) {
        List<Stat> stats = new ArrayList<>();
        stats.add(Stat.create("Ritmo cardíaco", "70 LPM"));
        stats.add(Stat.create("Temperatura", "36ºC"));
        callback.onSuccess(stats);
      }

      @Override public void unsubscribe() {

      }
    };
  }
}
