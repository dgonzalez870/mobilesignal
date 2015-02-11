package com.mobile.mobilesignal;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemsalarmaAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Item> items;

	public ItemsalarmaAdapter(Context context, ArrayList<Item> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (convertView == null) {
			// Create a new view into the list.
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater
					.inflate(R.layout.layout_itemlista, parent, false);
		}
		TextView tv_id_dato = (TextView) rowView.findViewById(R.id.text_id_dato);
		TextView tv_valor_dato = (TextView) rowView
				.findViewById(R.id.text_value_dato);
		Item item = this.items.get(position);
		tv_id_dato.setText(item.getIdDato());
		tv_valor_dato.setText(item.getValorDato());

		return rowView;
	}

}
