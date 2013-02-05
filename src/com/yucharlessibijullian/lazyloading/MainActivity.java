package com.yucharlessibijullian.lazyloading;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;

public class MainActivity extends ListActivity {

	private ArrayList<Image> images;
	private ImageAdapter imgAdp;
 	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Populate list of students
        images = new ArrayList<Image>();
        images.add(new Image("Image #1:", "http://ww1.prweb.com/prfiles/2010/05/11/1751474/gI_TodoforiPadAppIcon512.png.jpg"));
        images.add(new Image("Image #2:", "http://cdn4.iosnoops.com/wp-content/uploads/2011/08/Icon-Gmail_large-250x250.png"));
        images.add(new Image("Image #3:", "http://kelpbeds.files.wordpress.com/2012/02/lens17430451_1294953222linkedin-icon.jpg?w=450"));
        images.add(new Image("Image #4:", "http://snapknot.com/blog/wp-content/uploads/2010/03/facebook-icon-copy.jpg"));
        images.add(new Image("Image #5:", "https://lh3.googleusercontent.com/-ycDGy_fZVZc/AAAAAAAAAAI/AAAAAAAAAAc/Q0MmjxPCOzk/s250-c-k/photo.jpg"));
        
        // Instantiate base adapter and set as adapter
        imgAdp = new ImageAdapter(MainActivity.this, images);
        setListAdapter(imgAdp);
        
        // Start laoding images
        for(Image i : images){
        	i.loadImage(imgAdp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
