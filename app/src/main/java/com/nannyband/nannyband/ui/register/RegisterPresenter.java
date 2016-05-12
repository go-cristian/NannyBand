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

import com.nannyband.nannyband.data.register.Register;

public class RegisterPresenter {
  private final RegisterView view;
  private final Register register;

  public RegisterPresenter(RegisterView view, Register register) {

    this.view = view;
    this.register = register;
  }

  public void register() {
    view.showLoading();
    if (view.valid()) {
      register.register(view.user(), new Register.Callback() {
        @Override public void onSuccess() {
          view.login();
        }

        @Override public void onFailure() {
          view.showError();
          view.showContent();
        }
      });
    } else {
      view.showValidError();
    }
  }
}
