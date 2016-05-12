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

import com.nannyband.nannyband.data.configuration.BandSettings;
import com.nannyband.nannyband.data.configuration.SafetyZone;
import java.util.List;

public class BandSettingsPresenter {
  private final BandSettingView view;
  private final BandSettings settings;

  public BandSettingsPresenter(BandSettingView view, BandSettings settings) {
    this.view = view;
    this.settings = settings;
  }

  public void settings() {
    view.showLoading();
    settings.setting(new BandSettings.Callback() {
      @Override public void onSuccess(List<SafetyZone> zones) {
        view.renderZones(zones);
      }

      @Override public void onFailure() {
        view.showRetry();
      }
    });
  }
}
