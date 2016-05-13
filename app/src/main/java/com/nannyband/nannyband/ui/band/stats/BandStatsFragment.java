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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.stats.Stat;
import com.nannyband.nannyband.data.stats.Stats;
import com.nannyband.nannyband.ui.BaseFragment;
import com.nannyband.nannyband.ui.band.stats.widget.StatsWidget;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BandStatsFragment extends BaseFragment implements BandStatsView {
  @Inject Stats stats;
  @BindView(R.id.stats) StatsWidget statsView;
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.retry) View retryView;
  private BandStatsPresenter presenter;

  public static BaseFragment create() {
    return new BandStatsFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.stats, container, false);
    ButterKnife.bind(this, view);
    injector().inject(this);
    presenter = new BandStatsPresenter(this, stats);
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    presenter.startReceivingStats();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.stopReceivingStats();
  }

  @Override public void showLoading() {
    show(loadingView);
  }

  @Override public void showRetry() {
    show(retryView);
  }

  @Override public void showStats(List<Stat> stats) {
    show(statsView);
    statsView.show(stats);
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    retryView.setVisibility(GONE);
    statsView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
