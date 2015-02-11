package com.mobile.mobilesignal;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.*;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class DispositivoInfoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TelephonyManager manager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		//verifica el estado de la SIM para las opciones de la RED
		if(manager.getSimState()==TelephonyManager.SIM_STATE_READY){
			ArrayList<Item> items= new ArrayList<Item>();
			items.add(new Item("Operador de la SIM", manager.getSimOperator()));
			items.add(new Item("ID del dispositivo",manager.getDeviceId()));
			items.add(new Item("Operador de la Red",manager.getNetworkOperator()));
		}else{
			Toast.makeText(getApplicationContext(), "Se ha presentado un error en la Tarjeta SIM", Toast.LENGTH_LONG);
		}
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
	

}
