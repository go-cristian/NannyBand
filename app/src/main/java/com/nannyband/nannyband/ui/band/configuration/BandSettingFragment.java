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
package com.nannyband.nannyband.ui.band.configuration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.configuration.BandSettings;
import com.nannyband.nannyband.data.configuration.SafetyZone;
import com.nannyband.nannyband.ui.BaseFragment;
import com.nannyband.nannyband.ui.band.configuration.widget.SettingsWidget;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BandSettingFragment extends BaseFragment implements BandSettingView {
  @Inject BandSettings settings;
  @BindView(R.id.settings) SettingsWidget settingsWidget;
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.retry) View retryView;
  private BandSettingsPresenter presenter;

  public static BaseFragment create() {
    return new BandSettingFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.settings, container, false);
    ButterKnife.bind(this, view);
    injector().inject(this);
    presenter = new BandSettingsPresenter(this, settings);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.settings();
  }

  @Override public void showLoading() {
    show(loadingView);
  }

  @Override public void showRetry() {
    show(retryView);
  }

  @Override public void renderZones(List<SafetyZone> zones) {
    show(settingsWidget);
    settingsWidget.render(zones);
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    retryView.setVisibility(GONE);
    settingsWidget.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
