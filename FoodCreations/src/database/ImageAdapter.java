package database;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private Integer[] imageIds;
	
	public ImageAdapter(Context c, Integer[] imageids) {
		context = c;
		this.imageIds = imageids;
	}
	
	public int getCount() {
		return imageIds.length;
	}
	
	public Object getItem(int position) {
		return imageIds[position];
	}
	
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ImageView iview;
		if (view == null) {
			iview = new ImageView(context);
			iview.setLayoutParams(new GridView.LayoutParams(150,200));
			iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iview.setPadding(5, 5, 5, 5);
			
		} else {
			iview = (ImageView) view;	
		}
		iview.setImageResource(imageIds[position]); //imageid[position] = samples[position]
		return iview;
	}

	
}