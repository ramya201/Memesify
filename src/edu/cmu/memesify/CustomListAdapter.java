package edu.cmu.memesify;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter implements Filterable {

	private Context context;
	private ArrayList<String> meme_titles;
	private ArrayList<Bitmap> meme_pics;
	private LayoutInflater inflater;

	public CustomListAdapter(Context context, ArrayList<String> meme_titles, ArrayList<Bitmap> meme_pics) {
		this.context = context;
		this.meme_titles = meme_titles;
		this.meme_pics = meme_pics;        		
	}

	@Override
	public int getCount() {
		return meme_titles.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	public void remove(int arg0)
	{
		meme_titles.remove(arg0);
		meme_pics.remove(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView name;
		ImageView icon;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listitem, parent, false);

		name = (TextView) itemView.findViewById(R.id.titleTextView);
		icon = (ImageView) itemView.findViewById(R.id.icon);

		name.setText(meme_titles.get(position));
		icon.setImageBitmap(meme_pics.get(position));

		return itemView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
