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
package com.nannyband.nannyband.data.common;

import com.firebase.client.Firebase;
import com.nannyband.nannyband.App;
import com.nannyband.nannyband.BuildConfig;
import com.nannyband.nannyband.data.common.gps.AndroidGPS;
import com.nannyband.nannyband.data.common.gps.GPS;
import dagger.Module;
import dagger.Provides;

@Module public class CommonModule {

  private final App app;

  public CommonModule(App app) {
    this.app = app;
  }

  @Provides Firebase firebase() {
    Firebase.setAndroidContext(app);
    return new Firebase(BuildConfig.BASE_URL);
  }

  @Provides GPS gps() {
    return new AndroidGPS(app);
  }
}
