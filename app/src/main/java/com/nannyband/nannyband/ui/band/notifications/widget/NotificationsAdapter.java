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
package com.nannyband.nannyband.ui.band.notifications.widget;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.notifications.BandNotification;
import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationHolder> {

  private final List<BandNotification> notifications = new ArrayList<>();

  @Override public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NotificationHolder(
        from(parent.getContext()).inflate(R.layout.band_notification, parent, false));
  }

  @Override public void onBindViewHolder(NotificationHolder holder, int position) {
    holder.notification(notifications.get(position));
  }

  @Override public int getItemCount() {
    return notifications.size();
  }

  public void add(List<BandNotification> notifications) {
    this.notifications.clear();
    this.notifications.addAll(notifications);
    notifyDataSetChanged();
  }
}
