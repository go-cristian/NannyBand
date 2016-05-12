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
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.stats.Stat;
import java.util.ArrayList;
import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsHolder> {

  private final List<Stat> stats = new ArrayList<>();

  @Override public StatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new StatsHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.stat, parent, false));
  }

  @Override public void onBindViewHolder(StatsHolder holder, int position) {
    holder.stat(stats.get(position));
  }

  @Override public int getItemCount() {
    return stats.size();
  }

  public void stats(List<Stat> stats) {
    this.stats.clear();
    this.stats.addAll(stats);
    notifyDataSetChanged();
  }
}
