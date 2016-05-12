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
package com.nannyband.nannyband.data.configuration;

import android.location.Location;
import android.location.LocationManager;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;

@Module public class BandSettingsModule {
  @Provides public BandSettings bandSettings() {
    return new BandSettings() {
      @Override public void setting(Callback callback) {
        List<SafetyZone> zones = new ArrayList<>();
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(4.710989);
        location.setLongitude(-74.072092);
        zones.add(SafetyZone.create("Casa", location));
        callback.onSuccess(zones);
      }
    };
  }
}
