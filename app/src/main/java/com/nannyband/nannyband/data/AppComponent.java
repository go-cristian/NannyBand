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
package com.nannyband.nannyband.data;

import com.nannyband.nannyband.data.auth.AuthModule;
import com.nannyband.nannyband.data.configuration.BandSettingsModule;
import com.nannyband.nannyband.data.location.BandLocationModule;
import com.nannyband.nannyband.data.notifications.NotificationsModule;
import com.nannyband.nannyband.data.session.BandSessionModule;
import com.nannyband.nannyband.data.stats.StatsModule;
import com.nannyband.nannyband.ui.band.configuration.BandSettingFragment;
import com.nannyband.nannyband.ui.band.location.BandLocationFragment;
import com.nannyband.nannyband.ui.band.notifications.BandNotificationsFragment;
import com.nannyband.nannyband.ui.band.stats.BandStatsFragment;
import com.nannyband.nannyband.ui.login.LoginActivity;
import com.nannyband.nannyband.ui.splash.SplashActivity;
import dagger.Component;

@Component(modules = {
    AuthModule.class, BandSettingsModule.class, BandLocationModule.class, NotificationsModule.class,
    BandSessionModule.class, StatsModule.class
}) public interface AppComponent {
  void inject(BandLocationFragment fragment);

  void inject(BandStatsFragment fragment);

  void inject(BandNotificationsFragment fragment);

  void inject(BandSettingFragment fragment);

  void inject(LoginActivity activity);

  void inject(SplashActivity activity);
}
