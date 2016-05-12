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
import android.location.LocationManager;
import dagger.Module;
import dagger.Provides;

@Module public class BandLocationModule {
  @Provides public BandLocation bandLocation() {
    return new BandLocation() {
      @Override public void locate(Callback callback) {
        Location band = new Location(LocationManager.GPS_PROVIDER);
        band.setLatitude(4.710989);
        band.setLongitude(-74.072092);
        Location myself = new Location(LocationManager.GPS_PROVIDER);
        myself.setLatitude(4.710999);
        myself.setLongitude(-74.072102);
        callback.onBandLocated(band);
        callback.onMyselfLocated(myself);
      }
    };
  }
}
