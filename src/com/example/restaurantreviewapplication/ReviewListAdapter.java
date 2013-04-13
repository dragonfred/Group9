package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ReviewListAdapter extends BaseAdapter {
	private static ArrayList<Review> reviewDetailsArrayList;
	
	private LayoutInflater l_Inflater;

	public ReviewListAdapter(Context context, ArrayList<Review> results) {
		reviewDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return reviewDetailsArrayList.size();
	}

	public Object getItem(int position) {
		return reviewDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.review_details_list_item, null);
			holder = new ViewHolder();
			holder.img_review_image = (ImageView) convertView.findViewById(R.id.reviewPhoto);
			holder.txt_reviewer_name = (TextView) convertView.findViewById(R.id.reviewerName);
			holder.txt_restaurant_review = (TextView) convertView.findViewById(R.id.restaurantReview);
			holder.txt_review_rating = (RatingBar) convertView.findViewById(R.id.reviewRating);
			//holder.restaurant_image = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (reviewDetailsArrayList.get(position).getImage() != null)
			holder.img_review_image.setImageBitmap(reviewDetailsArrayList.get(position).getImage());
		holder.txt_reviewer_name.setText(reviewDetailsArrayList.get(position).getReviewer());
		holder.txt_restaurant_review.setText(reviewDetailsArrayList.get(position).getReview());				
		holder.txt_review_rating.setRating(reviewDetailsArrayList.get(position).getOverallRating());

		return convertView;
	}

	static class ViewHolder {
		ImageView img_review_image;
		TextView txt_reviewer_name;
		TextView txt_restaurant_review;
		RatingBar txt_review_rating;
	}
}
