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
package com.nannyband.nannyband.ui.band.stats;

import com.nannyband.nannyband.data.stats.Stat;
import com.nannyband.nannyband.data.stats.Stats;
import java.util.List;

public class BandStatsPresenter {
  private final BandStatsView view;
  private final Stats stats;

  private boolean updated;

  public BandStatsPresenter(BandStatsView view, Stats stats) {
    this.view = view;
    this.stats = stats;
  }

  public void startReceivingStats() {
    if (!updated) view.showLoading();
    stats.subscribe(new Stats.Callback() {
      @Override public void onSuccess(List<Stat> stats) {
        view.showStats(stats);
        updated = true;
      }

      @Override public void onFailure() {
        if (!updated) view.showRetry();
      }
    });
  }

  public void stopReceivingStats() {
    stats.unsubscribe();
  }
}
