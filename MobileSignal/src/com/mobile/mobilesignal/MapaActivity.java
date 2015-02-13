package com.mobile.mobilesignal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements
		OnMapReadyCallback, OnMapClickListener, OnMapLongClickListener {
	GoogleMap mapa;
	private int contador = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SupportMapFragment mapfrag = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapa));
		mapfrag.getMapAsync(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public void onMapReady(GoogleMap googleMap) {
		// TODO Auto-generated method stub
		mapa = googleMap;
		mapa.getUiSettings().setZoomControlsEnabled(true);
		mapa.setOnMapClickListener(this);
		mapa.setOnMapLongClickListener(this);
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		MarkerOptions opciones = new MarkerOptions();
		opciones.title("hola: " + arg0.latitude + " " + arg0.longitude);
		opciones.position(arg0);
		opciones.snippet("contador: " + contador);
		mapa.addMarker(opciones);
		contador++;
	}

	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub

	}
}
