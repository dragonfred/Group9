package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantListAdapter extends BaseAdapter {
	private static ArrayList<Restaurant> restaurantDetailsArrayList;
		
	private LayoutInflater l_Inflater;

	public RestaurantListAdapter(Context context, ArrayList<Restaurant> results) {
		restaurantDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return restaurantDetailsArrayList.size();
	}

	public Object getItem(int position) {
		return restaurantDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.restaurant_details, null);
			holder = new ViewHolder();
			holder.txt_restaurant_name = (TextView) convertView.findViewById(R.id.restaurant_name);
			holder.txt_restaurant_address = (TextView) convertView.findViewById(R.id.restaurant_address);
			holder.txt_restaurant_phone = (TextView) convertView.findViewById(R.id.restaurant_phone);
			//holder.restaurant_image = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_restaurant_name.setText(restaurantDetailsArrayList.get(position).getName());
		holder.txt_restaurant_address.setText(restaurantDetailsArrayList.get(position).getAddress());
		holder.txt_restaurant_phone.setText(restaurantDetailsArrayList.get(position).getPhone());
		//holder.restaurant_image.setImageResource(imgid[restaurantDetailsArrayList.get(position).getImage());
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_restaurant_name;
		TextView txt_restaurant_address;
		TextView txt_restaurant_phone;
		//ImageView restaurant_image;
	}
}
