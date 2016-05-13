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
package com.nannyband.nannyband.data.common.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.nannyband.nannyband.App;

@SuppressWarnings("MissingPermission") public class AndroidGPS implements GPS {

  private final App app;
  private LocationManager locationManager;
  private Callback callback;

  public AndroidGPS(App app) {
    this.app = app;
  }

  @Override public void locate(Callback callback) {
    this.callback = callback;
    locationManager = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if (location == null) {
      requestLocation();
    } else {
      callback.onLocated(location);
    }
  }

  private void requestLocation() {
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000,
        new LocationListener() {
          @Override public void onLocationChanged(Location location) {
            callback.onLocated(location);
          }

          @Override public void onStatusChanged(String provider, int status, Bundle extras) {

          }

          @Override public void onProviderEnabled(String provider) {

          }

          @Override public void onProviderDisabled(String provider) {
            callback.onFailure();
          }
        });
  }
}
