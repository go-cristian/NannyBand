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
package com.nannyband.nannyband;

import android.app.Application;
import com.nannyband.nannyband.data.AppComponent;
import com.nannyband.nannyband.data.DaggerAppComponent;
import com.nannyband.nannyband.data.auth.AuthModule;
import com.nannyband.nannyband.data.configuration.BandSettingsModule;
import com.nannyband.nannyband.data.location.BandLocationModule;
import com.nannyband.nannyband.data.notifications.NotificationsModule;
import com.nannyband.nannyband.data.session.BandSessionModule;
import com.nannyband.nannyband.data.stats.StatsModule;

public class App extends Application {

  public AppComponent injector;

  @Override public void onCreate() {
    super.onCreate();
    injector = DaggerAppComponent.builder()
        .authModule(new AuthModule())
        .bandLocationModule(new BandLocationModule())
        .bandSessionModule(new BandSessionModule())
        .bandSettingsModule(new BandSettingsModule())
        .notificationsModule(new NotificationsModule())
        .statsModule(new StatsModule())
        .build();
  }

  public AppComponent injector() {
    return injector;
  }
}
