package com.example.finalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	
	private Activity activity;
	ArrayList<GameObject> gameList;
	private static LayoutInflater inflater=null;
	DiskLruCache imageCache;
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; 
	
	public CustomAdapter(Activity a, ArrayList<GameObject> data) {
        activity = a;
        gameList = data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageCache = DiskLruCache.openCache(a.getApplicationContext(), a.getCacheDir(), DISK_CACHE_SIZE);

    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gameList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return gameList.get(position);
	}
	
	public void removeItem(int position){
		gameList.remove(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View vi=convertView;
		if(convertView==null){
            vi = inflater.inflate(R.layout.list_row, null);
		}
		
		TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView rating = (TextView)vi.findViewById(R.id.game_rating); 
        TextView platforms = (TextView)vi.findViewById(R.id.platforms);
        
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
//        ImageView critics_image=(ImageView)vi.findViewById(R.id.critics_rating);
//        ImageView audience_image=(ImageView)vi.findViewById(R.id.audience_rating);
        ImageLoaderTask imgLoader;
//        
        title.setText(gameList.get(position).getTitle());
        if(gameList.get(position).getRating()!=null)
        	rating.setText(gameList.get(position).getRating().toString());
        
        int i;
        String plats = "";
        ArrayList<String> plat = gameList.get(position).getPlatforms();
        if(plat!=null){
	        for(i=0;i<plat.size()-1; i++){
	        	plats = plats + plat.get(i) + ",";
	        }
	        plats = plats + plat.get(i);
	        platforms.setText(plats);
        }
//        String critics_rating = moviesList.get(position).getCritics_rating();
//        String aud_rating = moviesList.get(position).getAudience_rating();
//        
//        if(critics_rating==null)
//        	critics_image.setImageResource(R.drawable.notranked);
//        else if(critics_rating.equalsIgnoreCase("Certified Fresh"))
//        	critics_image.setImageResource(R.drawable.certified_fresh);
//        else if(critics_rating.equalsIgnoreCase("Fresh"))
//        	critics_image.setImageResource(R.drawable.fresh);
//        else if(critics_rating.equalsIgnoreCase("Rotten"))
//        	critics_image.setImageResource(R.drawable.rotten);
//        if(aud_rating == null)
//        	audience_image.setImageResource(R.drawable.notranked);
//        else if(aud_rating.equalsIgnoreCase("Upright"))
//        	audience_image.setImageResource(R.drawable.upright);
//        else if(aud_rating.equalsIgnoreCase("Spilled"))
//        	audience_image.setImageResource(R.drawable.spilled);
//        else if(aud_rating.equalsIgnoreCase("Not Ranked"))
//        	audience_image.setImageResource(R.drawable.notranked);
//       
        if(imageCache.containsKey(gameList.get(position).getTitle())){
        	Bitmap bmp = imageCache.get(gameList.get(position).getTitle());
        	thumb_image.setImageBitmap(bmp);
        }
        else{
	        String thumb_url = gameList.get(position).getThumbUrl();
	        if(thumb_url != null){
	        	imgLoader = new ImageLoaderTask(thumb_image, thumb_url, imageCache, gameList.get(position).getTitle());
	        	imgLoader.execute();
	        }
	        else{
	        	thumb_image.setImageResource(R.drawable.ic_launcher);
	        }
        }
		return vi;
	}
	public void removeAllItems() {
		// TODO Auto-generated method stub
		gameList.clear();
	}

}
