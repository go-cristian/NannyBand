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
package com.nannyband.nannyband.ui.band.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.location.BandLocation;
import com.nannyband.nannyband.ui.BaseFragment;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BandLocationFragment extends BaseFragment
    implements BandLocationView, OnMapReadyCallback {

  private GoogleMap googleMap;
  private Location band;
  private Location myself;

  public static BaseFragment create() {
    return new BandLocationFragment();
  }

  @Inject BandLocation location;
  @BindView(R.id.location) MapView locationView;
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.retry) View retryView;
  private BandLocationPresenter presenter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.location, container, false);
    ButterKnife.bind(this, view);
    injector().inject(this);
    presenter = new BandLocationPresenter(this, location);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.bandLocation();
  }

  @Override public void showLoading() {
    show(loadingView);
  }

  @Override public void showRetry() {
    show(retryView);
  }

  @Override public void showBandLocation(Location band) {
    show(locationView);
    locationView.getMapAsync(this);
    this.band = band;
  }

  @Override public void showMyLocation(Location myself) {
    show(locationView);
    this.myself = myself;
    if (googleMap != null) {
      googleMap.addMarker(
          new MarkerOptions().position(new LatLng(myself.getLatitude(), myself.getLongitude())));
    }
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    this.googleMap = googleMap;
    googleMap.addMarker(
        new MarkerOptions().position(new LatLng(band.getLatitude(), band.getLongitude())));
    if (myself != null) {
      googleMap.addMarker(
          new MarkerOptions().position(new LatLng(myself.getLatitude(), myself.getLongitude())));
    }
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    retryView.setVisibility(GONE);
    locationView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
