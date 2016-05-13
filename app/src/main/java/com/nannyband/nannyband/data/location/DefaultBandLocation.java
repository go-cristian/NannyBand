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
package com.nannyband.nannyband.data.location;

import android.location.Location;
import com.nannyband.nannyband.data.common.gps.GPS;
import com.nannyband.nannyband.data.location.service.LocationService;

public class DefaultBandLocation implements BandLocation {
  private final GPS gps;
  private final LocationService service;

  public DefaultBandLocation(GPS gps, LocationService service) {
    this.gps = gps;
    this.service = service;
  }

  @Override public void locate(final Callback callback) {
    gps.locate(new GPS.Callback() {
      @Override public void onLocated(Location location) {
        callback.onMyselfLocated(location);
      }

      @Override public void onFailure() {
        //do nothing
      }
    });
    service.locate(callback);
  }
}
