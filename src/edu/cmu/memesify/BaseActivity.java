package edu.cmu.memesify;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;

public class BaseActivity extends Activity {
	protected ArrayList<String> meme_titles;
	protected ArrayList<Bitmap> meme_pics;
	protected ArrayList<Bitmap> meme_pics_editable;
	protected ArrayList<String> defaultTopText;
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		
		meme_titles = new ArrayList<String>();
		meme_pics = new ArrayList<Bitmap>();
		meme_pics_editable = new ArrayList<Bitmap>();
		defaultTopText = new ArrayList<String>();
		
		meme_titles = new ArrayList<String>();
		meme_titles.add("Futurama Fry");
		meme_titles.add("Bad Luck Brian");
		meme_titles.add("Am I the only one");
		meme_titles.add("One does not simply");
		meme_titles.add("Skeptical Baby");
		meme_titles.add("First World Problems");
		meme_titles.add("I don't always");
		meme_titles.add("Prepare Yourself");
		
		Options bitmapOptions = new Options();
		bitmapOptions.inMutable = true;

		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme1eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme2eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme3eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme4eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme5eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme6eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme7eg));
		meme_pics.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme8eg));
		
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme1, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme2, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme3, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme4, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme5, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme6, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme7, bitmapOptions));
		meme_pics_editable.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.meme8, bitmapOptions));
		
		
		defaultTopText.add("Not sure if");
		defaultTopText.add("");
		defaultTopText.add("Am I the only one");
		defaultTopText.add("One does not simply");
		defaultTopText.add("Are you telling me");
		defaultTopText.add("");
		defaultTopText.add("I don't always");
		defaultTopText.add("Prepare yourself");		
	}
}
