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

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.session.BandSession;
import com.nannyband.nannyband.ui.BaseActivity;
import com.nannyband.nannyband.ui.band.BandActivity;
import com.nannyband.nannyband.ui.login.LoginActivity;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

  @Inject BandSession session;
  private SplashPresenter presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);
    injector().inject(this);
    presenter = new SplashPresenter(this, session);
  }

  @Override public void mainView() {
    startActivity(BandActivity.intent(this));
    finish();
  }

  @Override public void loginView() {
    startActivity(LoginActivity.intent(this));
    finish();
  }
}
