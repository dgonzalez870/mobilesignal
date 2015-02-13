package com.mobile.mobilesignal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RedInfoActivity extends Activity {

	private ListView lista;

	public class EstadoTelefonoListener extends PhoneStateListener {

		@Override
		public void onCellLocationChanged(CellLocation location) {
			// TODO Auto-generated method stub
			super.onCellLocationChanged(location);
		}

		@Override
		public void onServiceStateChanged(ServiceState serviceState) {
			// TODO Auto-generated method stub
			super.onServiceStateChanged(serviceState);
		}

		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			// TODO Auto-generated method stub
			super.onSignalStrengthsChanged(signalStrength);
			Log.i("RED_INFO", "Nivel de señal: " + signalStrength.getCdmaDbm());
			View vista = lista.getChildAt(1);
			TextView tv_dato = (TextView) vista
					.findViewById(R.id.text_value_dato);
			tv_dato.setText(signalStrength.getGsmSignalStrength());
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// tipo de red
		Log.i("RED_INFO", "Tipo de red: " + manager.getNetworkType());
		String tipoDeRed = "default";
		if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_1xRTT)
			tipoDeRed = "1xRTT";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_CDMA)
			tipoDeRed = "CDMS";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE)
			tipoDeRed = "EDGE";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS)
			tipoDeRed = "GPRS";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS)
			tipoDeRed = "UMTS";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EVDO_0)
			tipoDeRed = "EVDO_0";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EVDO_A)
			tipoDeRed = "EVDO_A";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EVDO_B)
			tipoDeRed = "EVDO_B";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA)
			tipoDeRed = "HSDPA";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA)
			tipoDeRed = "HSPA";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA)
			tipoDeRed = "HSUPA";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_IDEN)
			tipoDeRed = "IDEN";
		else if (manager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UNKNOWN)
			tipoDeRed = "UNKNOWN";

		EstadoTelefonoListener listener = new EstadoTelefonoListener();
		// verifica el estado de la SIM para las opciones de la RED
		if (manager.getSimState() == TelephonyManager.SIM_STATE_READY) {
			List<NeighboringCellInfo> cellinfo = manager
					.getNeighboringCellInfo();
			Log.i("RED_INFO", "Información de la red " + cellinfo.size());
			for (int i = 0; i < cellinfo.size(); i++) {
				Log.i("RED_INFO", i + " " + "CID " + cellinfo.get(i).getCid());
				Log.i("RED_INFO", i + " " + "LAC " + cellinfo.get(i).getLac());
				Log.i("RED_INFO", i + " " + "TIPO "
						+ cellinfo.get(i).getNetworkType());
				Log.i("RED_INFO", i + " " + "RSSI " + cellinfo.get(i).getRssi());
			}
			ArrayList<Item> items = new ArrayList<Item>();
			String operador = "default"
					+ manager.getSimOperator().substring(3, 5);
			String pais = manager.getSimOperator().substring(0, 3);
			// Identifica el país de la red
			if (pais.equals("734"))
				pais = "Venezuela";
			else
				pais = "default";

			if (manager.getSimOperator().substring(3, 5).equals("02"))
				operador = "DIGITEL";
			else if (manager.getSimOperator().substring(3, 5).equals("04"))
				operador = "MOVISTAR";
			else if (manager.getSimOperator().substring(3, 5).equals("06"))
				operador = "MOVILNET";
			/**
			 * Información de localización
			 */
			// Acquire a reference to the system Location Manager
			LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 5000, 10, listenerLocalizacion);
			Criteria criteria = new Criteria();
		    String provider = locationManager.getBestProvider(criteria, false);
		    Location location = locationManager.getLastKnownLocation(provider);
			Log.i("RED_INFO","Localización: "+location.getLatitude()+" "+location.getLongitude()+" "+location.getSpeed());
			
			
			items.add(new Item("País", pais));
			items.add(new Item("Operador de la Red", operador));
			items.add(new Item("Tipo de Red", tipoDeRed));
			items.add(new Item("Nivel de señal", "0.0"));
			setContentView(R.layout.layout_main);
			lista = (ListView) findViewById(R.id.lista_actividades);
			lista.setAdapter(new ItemsalarmaAdapter(getApplicationContext(),
					items));
			manager.listen(listener, PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
		} else {
			Toast.makeText(getApplicationContext(),
					"Se ha presentado un error en la Tarjeta SIM",
					Toast.LENGTH_LONG).show();
		}
	}

	LocationListener listenerLocalizacion= new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Log.i("RED_INFO","Se ha habilitado el proveedor de localización");
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			Log.i("RED_INFO","Localización: "+location.getLatitude()+" "+location.getLongitude()+" "+location.getSpeed());
		}
	};
	
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

}
