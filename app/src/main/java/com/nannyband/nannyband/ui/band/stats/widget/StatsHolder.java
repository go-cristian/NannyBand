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
package com.nannyband.nannyband.ui.band.stats.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.stats.Stat;

public class StatsHolder extends RecyclerView.ViewHolder {
  private final TextView titleView;
  private final TextView valueView;

  public StatsHolder(View itemView) {
    super(itemView);
    titleView = (TextView) itemView.findViewById(R.id.stat_title);
    valueView = (TextView) itemView.findViewById(R.id.stat_value);
  }

  public void stat(Stat stat) {
    titleView.setText(stat.title());
    valueView.setText(stat.value());
  }
}
