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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nannyband.nannyband.data.location.service.LocationService;
import java.util.HashMap;

public class FirebaseLocationService implements LocationService {
  private final Firebase firebase;

  public FirebaseLocationService(Firebase firebase) {
    this.firebase = firebase;
  }

  @Override public void locate(final BandLocation.Callback callback) {
    firebase.child("location").addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onBandLocated(to(dataSnapshot));
      }

      @Override public void onCancelled(FirebaseError firebaseError) {
        callback.onFailure();
      }
    });
  }

  private Location to(DataSnapshot dataSnapshot) {
    Location location = new Location(LocationManager.GPS_PROVIDER);
    HashMap map = (HashMap) dataSnapshot.getValue();
    location.setLongitude(Double.parseDouble(map.get("lon").toString()));
    location.setLatitude(Double.parseDouble(map.get("lat").toString()));
    return location;
  }
}
