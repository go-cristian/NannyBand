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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.configuration.SafetyZone;
import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingHolder> {

  private final List<SafetyZone> zones = new ArrayList<>();

  @Override public SettingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SettingHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.setting, parent, false));
  }

  @Override public void onBindViewHolder(SettingHolder holder, int position) {
    holder.safetyZone(zones.get(position));
  }

  @Override public int getItemCount() {
    return zones.size();
  }

  public void show(List<SafetyZone> zones) {
    this.zones.addAll(zones);
    notifyDataSetChanged();
  }
}
