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
package com.nannyband.nannyband.data.register;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class RegistrationUser {
  public static RegistrationUser create(String username, String password, String passwordRepeat) {
    return new AutoValue_RegistrationUser(username, password, passwordRepeat);
  }

  public abstract String username();

  public abstract String password();

  public abstract String passwordRepeat();

  public boolean isValidPassword() {
    return password() == passwordRepeat();
  }
}
