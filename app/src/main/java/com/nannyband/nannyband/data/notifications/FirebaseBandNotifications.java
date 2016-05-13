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
package com.nannyband.nannyband.data.notifications;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseBandNotifications implements BandNotifications {

  private final Firebase firebase;

  public FirebaseBandNotifications(Firebase firebase) {
    this.firebase = firebase;
  }

  @Override public void subscribe(final Callback callback) {
    firebase.child("notifications").addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onSuccess(to(dataSnapshot));
      }

      @Override public void onCancelled(FirebaseError firebaseError) {
        callback.onFailure();
      }
    });
  }

  private List<BandNotification> to(DataSnapshot dataSnapshot) {
    ArrayList<HashMap> list = (ArrayList) dataSnapshot.getValue();
    List<BandNotification> notifications = new ArrayList<>(list.size());
    for (HashMap map : list) {
      notifications.add(
          BandNotification.create(map.get("title").toString(), map.get("description").toString(),
              map.get("created").toString()));
    }
    return notifications;
  }
}
