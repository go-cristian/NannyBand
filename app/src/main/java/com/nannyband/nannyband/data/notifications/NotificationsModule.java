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

import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;

@Module public class NotificationsModule {
  @Provides public BandNotifications bandNotifications() {
    return new BandNotifications() {
      @Override public void subscribe(Callback callback) {
        List<BandNotification> notifications = new ArrayList<>();
        notifications.add(BandNotification.create("Notificacion", "Descripcion", "hace dos dias"));
        notifications.add(BandNotification.create("Notificacion", "Descripcion", "hace dos dias"));
        notifications.add(BandNotification.create("Notificacion", "Descripcion", "hace dos dias"));
        callback.onSuccess(notifications);
      }
    };
  }
}
