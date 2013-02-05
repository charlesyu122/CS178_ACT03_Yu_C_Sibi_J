package com.yucharlessibijullian.lazyloading;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class Image {
	
	// Attributes
	private String name;
	private String imgUrl;
	private Bitmap image;
	private ImageAdapter imgAdp;
	
	// Constructor
	public Image(String name, String url){
		this.name = name;
		this.imgUrl = url;
		this.image = null;
	}
	
	// Methods
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getImgUrl(){
		return this.imgUrl;
	}
	
	public void setImgUrl(String url){
		this.imgUrl = url;
	}
	
	public Bitmap getImage(){
		return this.image;
	}
	
	public ImageAdapter getAdapter(){
		return this.imgAdp;
	}
	
	public void setAdapter(ImageAdapter ad){
		this.imgAdp = ad;
	}
	
	public void loadImage(ImageAdapter adp){
		this.imgAdp = adp;
		if(imgUrl != null && !imgUrl.equals("")){
			new ImageLoadTask().execute(imgUrl);
		}
	}
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	// Async task to load lazy load images
	private class ImageLoadTask extends AsyncTask<String, String, Bitmap>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Log.d("ImageLoadTask", "Loading image...");
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.d("ImageLoadTask", "Attempting to load image URL: " + params[0]);
			try{
				Bitmap b = getBitmapFromURL(params[0]);
				return b;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			if(result != null){
				Log.d("ImageLoadTask", "Successfully loaded " + name + " image");
				image = result;
				if(imgAdp != null){
					// When the image is loaded notify the adapter
					imgAdp.notifyDataSetChanged();
				}
			} else {
				Log.d("ImageLoadTask", "Failed to load " + name + " image");
			}
		}
		
	}
	
	
	

}
