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
package com.nannyband.nannyband.ui.band.notifications;

import com.nannyband.nannyband.data.notifications.BandNotification;
import com.nannyband.nannyband.data.notifications.BandNotifications;
import java.util.ArrayList;
import java.util.List;

public class BandNotificationsPresenter {
  private final BandNotificationsView view;
  private final BandNotifications notifications;
  private boolean updated;

  public BandNotificationsPresenter(BandNotificationsView view, BandNotifications notifications) {
    this.view = view;
    this.notifications = notifications;
  }

  public void subscribeNotifications() {
    if (!updated) view.showLoading();
    notifications.subscribe(new BandNotifications.Callback() {

      @Override public void onSuccess(List<BandNotification> notifications) {
        view.updateNotifications(notifications);
      }

      @Override public void onFailure() {
        if (!updated) view.showRetry();
      }
    });
  }

  public void unsubscribeNotifications() {

  }
}
