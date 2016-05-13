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
package com.nannyband.nannyband.data.stats;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseStats implements Stats {
  private final Firebase firebase;

  public FirebaseStats(Firebase firebase) {
    this.firebase = firebase;
  }

  @Override public void subscribe(final Callback callback) {
    firebase.child("stats").addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        callback.onSuccess(to(dataSnapshot));
      }

      @Override public void onCancelled(FirebaseError firebaseError) {
        callback.onFailure();
      }
    });
  }

  private List<Stat> to(DataSnapshot dataSnapshot) {
    ArrayList<HashMap> list = (ArrayList) dataSnapshot.getValue();
    List<Stat> stats = new ArrayList<>(list.size());
    for (HashMap map : list) {
      stats.add(Stat.create(map.get("title").toString(), map.get("value").toString()));
    }
    return stats;
  }

  @Override public void unsubscribe() {

  }
}
