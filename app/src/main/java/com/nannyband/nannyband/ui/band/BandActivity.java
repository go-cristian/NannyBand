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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.ui.BaseActivity;
import com.nannyband.nannyband.ui.band.configuration.BandSettingFragment;
import com.nannyband.nannyband.ui.band.location.BandLocationFragment;
import com.nannyband.nannyband.ui.band.notifications.BandNotificationsFragment;
import com.nannyband.nannyband.ui.band.stats.BandStatsFragment;

public class BandActivity extends BaseActivity {

  public static Intent intent(BaseActivity activity) {
    return new Intent(activity, BandActivity.class);
  }

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewpager) ViewPager pager;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.band);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    pager.setAdapter(new Adapter(getSupportFragmentManager()));
    tabs.setupWithViewPager(pager);
    tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
  }

  private class Adapter extends FragmentPagerAdapter {

    public Adapter(FragmentManager fm) {
      super(fm);
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

    @Override public CharSequence getPageTitle(int position) {
      String title;
      int icon;
      switch (position) {
        case 0:
          title = getString(R.string.location_title);
          icon = R.drawable.ic_add_location_black_24dp;
          break;
        case 1:
          title = getString(R.string.stats_title);
          icon = R.drawable.ic_status_check_black_24dp;
          break;
        case 2:
          title = getString(R.string.notifications_title);
          icon = R.drawable.ic_notification_black_24dp;
          break;
        case 3:
          title = getString(R.string.settings_title);
          icon = R.drawable.ic_settings_black_24dp;
          break;
        default:
          throw new IllegalStateException();
      }

      Drawable image = getResources().getDrawable(icon);
      image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
      SpannableString sb = new SpannableString("   " + title);
      ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
      sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      return sb;
    }
  }
}
