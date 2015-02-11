package com.mobile.mobilesignal;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		String[] actividades={ getString(R.string.act_datos_tel), 
				              getString(R.string.act_datos_red),
				              getString(R.string.act_test_datos),
				              getString(R.string.act_test_llam),
				              getString(R.string.act_info_geo)};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1,actividades );
		ListView lista_actividades=(ListView)findViewById(R.id.lista_actividades);
		lista_actividades.setAdapter(adapter);
	}}
