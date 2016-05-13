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
package com.nannyband.nannyband.ui.band;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.ui.BaseActivity;
import com.nannyband.nannyband.ui.band.configuration.BandSettingFragment;
import com.nannyband.nannyband.ui.band.location.BandLocationFragment;
import com.nannyband.nannyband.ui.band.notifications.BandNotificationsFragment;
import com.nannyband.nannyband.ui.band.stats.BandStatsFragment;

public class BandActivity extends BaseActivity {

  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewpager) ViewPager pager;

  public static Intent intent(BaseActivity activity) {
    return new Intent(activity, BandActivity.class);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.band);
    ButterKnife.bind(this);
    Adapter adapter = new Adapter(this);
    pager.setAdapter(adapter);
    pager.setOffscreenPageLimit(4);
    tabs.setupWithViewPager(pager);
    tabs.setTabMode(TabLayout.MODE_FIXED);
    for (int i = 0; i < tabs.getTabCount(); i++) {
      TabLayout.Tab tab = tabs.getTabAt(i);
      tab.setCustomView(adapter.getTabView(i));
    }
  }

  private class Adapter extends FragmentPagerAdapter {

    private final BaseActivity activity;

    public Adapter(BaseActivity activity) {
      super(activity.getSupportFragmentManager());
      this.activity = activity;
    }

    @Override public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return BandLocationFragment.create();
        case 1:
          return BandStatsFragment.create();
        case 2:
          return BandNotificationsFragment.create();
        case 3:
          return BandSettingFragment.create();
      }
      throw new IllegalStateException();
    }

    @Override public int getCount() {
      return 4;
    }

    public @NonNull View getTabView(int position) {

      String title;
      int icon;
      switch (position) {
        case 0:
          title = getString(R.string.location_title);
          icon = R.drawable.ic_action_location;
          break;
        case 1:
          title = getString(R.string.stats_title);
          icon = R.drawable.ic_action_alert;
          break;
        case 2:
          title = getString(R.string.notifications_title);
          icon = R.drawable.ic_action_notifications;
          break;
        case 3:
          title = getString(R.string.settings_title);
          icon = R.drawable.ic_action_settings;
          break;
        default:
          throw new IllegalStateException();
      }

      View tab = LayoutInflater.from(activity).inflate(R.layout.tab, null);
      TextView tabText = (TextView) tab.findViewById(R.id.tab_title);
      tabText.setText(title);
      tabText.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0);
      if (position == 0) {
        tab.setSelected(true);
      }
      return tab;
    }
  }
}
