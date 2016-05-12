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
package com.nannyband.nannyband.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.auth.Auth;
import com.nannyband.nannyband.ui.BaseActivity;
import com.nannyband.nannyband.ui.band.BandActivity;
import com.nannyband.nannyband.ui.register.RegisterActivity;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends BaseActivity implements LoginView {
  private static final int CODE = 100;

  public static Intent intent(BaseActivity activity) {
    return new Intent(activity, LoginActivity.class);
  }

  @Inject Auth auth;

  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.login) View loginView;
  @BindView(R.id.login_username) EditText usernameInputView;
  @BindView(R.id.login_password) EditText passwordInputView;
  private LoginPresenter presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    ButterKnife.bind(this);
    injector().inject(this);
    presenter = new LoginPresenter(this, auth);
  }

  @OnClick(R.id.login_button) public void login() {
    presenter.login();
  }

  @OnClick(R.id.login_register) public void register() {
    startActivityForResult(RegisterActivity.intent(this), CODE);
  }

  @Override public void mainView() {
    startActivity(BandActivity.intent(this));
    finish();
  }

  @Override public void showError() {
    Snackbar.make(loginView, R.string.login_error, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showContent() {
    show(loginView);
  }

  @Override public String password() {
    return passwordInputView.getText().toString();
  }

  @Override public String username() {
    return usernameInputView.getText().toString();
  }

  @Override public void showLoading() {
    show(loadingView);
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    loginView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
