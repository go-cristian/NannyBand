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
package com.nannyband.nannyband.ui.register;

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
import com.nannyband.nannyband.data.register.Register;
import com.nannyband.nannyband.data.register.RegistrationUser;
import com.nannyband.nannyband.ui.BaseActivity;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RegisterActivity extends BaseActivity implements RegisterView {
  public static Intent intent(BaseActivity activity) {
    return new Intent(activity, RegisterActivity.class);
  }

  @Inject Register register;
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.register) View registerView;
  @BindView(R.id.register_username) EditText usernameInputView;
  @BindView(R.id.register_password) EditText passwordInputView;
  @BindView(R.id.register_password_repeat) EditText passwordRepeatInputView;
  private RegisterPresenter presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);
    ButterKnife.bind(this);
    injector().inject(this);
    presenter = new RegisterPresenter(this, register);
  }

  @OnClick(R.id.register_button) public void register() {
    presenter.register();
  }

  @Override public void showContent() {
    show(registerView);
  }

  @Override public boolean valid() {
    return usernameInputView.getText().toString().length() > 0 && user().isValidPassword();
  }

  @Override public void showLoading() {
    show(loadingView);
  }

  @Override public void showError() {
    Snackbar.make(registerView, R.string.register_error, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showValidError() {
    Snackbar.make(registerView, R.string.register_valid_error, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void login() {
    setResult(RESULT_OK);
    finish();
  }

  @Override public RegistrationUser user() {
    return RegistrationUser.create(usernameInputView.getText().toString(),
        passwordInputView.getText().toString(), passwordRepeatInputView.getText().toString());
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    registerView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
