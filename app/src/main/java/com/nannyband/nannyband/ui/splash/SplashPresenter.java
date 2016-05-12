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
package com.nannyband.nannyband.ui.splash;

import com.nannyband.nannyband.BuildConfig;
import com.nannyband.nannyband.data.session.BandSession;
import java.util.Timer;
import java.util.TimerTask;

public class SplashPresenter {
  private final SplashView view;
  private final BandSession session;

  public SplashPresenter(SplashView view, BandSession session) {
    this.view = view;
    this.session = session;
    startTimer(BuildConfig.SPLASH_TIME);
  }

  private void startTimer(int splashTime) {
    new Timer().schedule(new TimerTask() {
      @Override public void run() {
        if (session.isValid()) {
          view.mainView();
        } else {
          view.loginView();
        }
      }
    }, splashTime);
  }
}
