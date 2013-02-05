package com.yucharlessibijullian.lazyloading;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{

	// Attributes
	private LayoutInflater inflater;
	private ArrayList<Image> images = new ArrayList<Image>();
	
	// Constructor
	public ImageAdapter(Context context, ArrayList<Image> images){
		inflater = LayoutInflater.from(context);
		this.images = images;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		Image i = images.get(position);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.single_image_row, null);
			holder = new ViewHolder();
			holder.tvImageName = (TextView) convertView.findViewById(R.id.tvImgName);
			holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
			holder.pbLoading = (ProgressBar) convertView.findViewById(R.id.pbLoading);
			convertView.setTag(holder);
		} else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvImageName.setText(i.getName());
		if(i.getImage() != null){
			// Display Image
			holder.ivImage.setVisibility(View.VISIBLE);
			holder.ivImage.setImageBitmap(i.getImage());
			holder.pbLoading.setVisibility(View.GONE);
		} else{
			// Loading
			holder.ivImage.setVisibility(View.GONE);
			holder.pbLoading.setVisibility(View.VISIBLE);
		}
		return convertView;
	}
	
	static class ViewHolder{
		TextView tvImageName;
		ImageView ivImage;
		ProgressBar pbLoading;
	}

}
