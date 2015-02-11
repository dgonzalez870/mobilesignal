package com.mobile.mobilesignal;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		lista_actividades.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplicationContext(), DispositivoInfoActivity.class));
					break;
				case 1:
					break;
				case 2:
					break;
				case 4:
					break;
				default:
					break;
				}
			}
		});
	}}
