package database;

import creativecreations.foodcreations.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameLayoutAdapter extends BaseAdapter {
	LayoutInflater mLayoutInflater;
	private Context context;
	private Integer[] samples = {
            R.drawable.ic_launcher, R.drawable.ic_launcher, 
            R.drawable.ic_launcher, R.drawable.ic_launcher
	};
	
	public FrameLayoutAdapter(Context c) {
		context = c;
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return samples.length;
	}
	
	public Object getItem(int position) {
		return samples[position];
	}
	
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    // Inflate the single grid item layout
	    if (convertView == null) {  
	        convertView = mLayoutInflater.inflate(R.layout.grid_item, parent, false);
	    }

	    // Set Image
	    ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
	    if (thumbnail != null) {
	        thumbnail.setImageResource(samples[position]);
	    }

	    // Set Text
	    TextView title = (TextView) convertView.findViewById(R.id.title);
	    if (title != null) {
	        title.setText("Image Number: " + position);
	    }

	    return convertView;     
	}

	
}