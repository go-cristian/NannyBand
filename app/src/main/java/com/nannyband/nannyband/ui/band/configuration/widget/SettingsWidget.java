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
package com.nannyband.nannyband.ui.band.configuration.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.nannyband.nannyband.data.configuration.SafetyZone;
import com.nannyband.nannyband.ui.band.configuration.widget.SettingsAdapter;
import java.util.List;

public class SettingsWidget extends RecyclerView {

  private SettingsAdapter adapter;

  public SettingsWidget(Context context) {
    super(context);
    init();
  }

  public SettingsWidget(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SettingsWidget(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    setLayoutManager(new LinearLayoutManager(getContext()));
    adapter = new SettingsAdapter();
    setAdapter(adapter);
  }

  public void render(List<SafetyZone> zones) {
    adapter.show(zones);
  }
}
