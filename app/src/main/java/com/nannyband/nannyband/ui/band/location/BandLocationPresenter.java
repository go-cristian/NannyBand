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
package com.nannyband.nannyband.ui.band.location;

import android.location.Location;
import com.nannyband.nannyband.data.location.BandLocation;

public class BandLocationPresenter {
  private final BandLocationView view;
  private final BandLocation location;

  public BandLocationPresenter(BandLocationView view, BandLocation location) {
    this.view = view;
    this.location = location;
  }

  public void bandLocation() {
    view.showLoading();
    location.locate(new BandLocation.Callback() {
      @Override public void onMyselfLocated(Location myself) {
        view.showMyLocation(myself);
      }

      @Override public void onBandLocated(Location band) {
        view.showBandLocation(band);
      }

      @Override public void onFailure() {
        view.showRetry();
      }
    });
  }
}
