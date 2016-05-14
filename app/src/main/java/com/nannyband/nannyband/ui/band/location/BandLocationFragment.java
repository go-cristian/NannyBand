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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nannyband.nannyband.R;
import com.nannyband.nannyband.data.location.BandLocation;
import com.nannyband.nannyband.ui.BaseFragment;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BandLocationFragment extends BaseFragment
    implements BandLocationView, OnMapReadyCallback {

  private static final int PERMISSION_REQUEST_CODE = 100;

  @Inject BandLocation location;
  @BindView(R.id.location) MapView locationView;
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.retry) View retryView;
  private GoogleMap googleMap;
  private Marker position;
  private Location band;
  private Location myself;
  private BandLocationPresenter presenter;

  public static BaseFragment create() {
    return new BandLocationFragment();
  }

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
    locationView.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkPermission()) {
        presenter.bandLocation();
      } else {
        requestPermissions();
      }
    } else {
      presenter.bandLocation();
    }
  }

  @Override public void onResume() {
    super.onResume();
    locationView.onResume();
  }

  @SuppressLint("NewApi") private void requestPermissions() {
    ActivityCompat.requestPermissions(getActivity(), new String[] {
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    }, PERMISSION_REQUEST_CODE);
  }

  @SuppressLint("NewApi") private boolean checkPermission() {
    return ContextCompat.checkSelfPermission(getActivity(),
        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(getActivity(),
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION_REQUEST_CODE
        && grantResults[0] == PackageManager.PERMISSION_GRANTED
        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
      presenter.bandLocation();
    } else {
      Toast.makeText(getContext(), R.string.location_error, Toast.LENGTH_SHORT).show();
    }
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
    locateBand();
  }

  @Override public void showMyLocation(Location myself) {
    show(locationView);
    this.myself = myself;
    locateMyself();
  }

  private void locateMyself() {
    if (googleMap != null && myself != null) {
      LatLng myselfLocation = new LatLng(myself.getLatitude(), myself.getLongitude());
      googleMap.addMarker(new MarkerOptions().position(myselfLocation));
    }
  }

  private void locateBand() {
    if (googleMap != null && band != null) {
      LatLng bandLocation = new LatLng(band.getLatitude(), band.getLongitude());
      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bandLocation, 15));

      if (position == null) {
        position = googleMap.addMarker(new MarkerOptions().position(bandLocation));
      }
      position.setPosition(bandLocation);
    }
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    this.googleMap = googleMap;
    googleMap.getUiSettings().setScrollGesturesEnabled(false);
    locateBand();
    locateMyself();
  }

  private void show(View view) {
    loadingView.setVisibility(GONE);
    retryView.setVisibility(GONE);
    locationView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
