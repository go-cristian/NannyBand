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

import com.nannyband.nannyband.data.auth.Auth;

public class LoginPresenter {
  private final LoginView view;
  private final Auth auth;

  public LoginPresenter(LoginView view, Auth auth) {
    this.view = view;
    this.auth = auth;
    view.showContent();
  }

  public void login() {
    view.showLoading();
    auth.login(view.password(), new Auth.Callback() {
      @Override public void onSuccess() {
        view.mainView();
      }

      @Override public void onFailure() {
        view.showError();
        view.showContent();
      }
    });
  }
}
