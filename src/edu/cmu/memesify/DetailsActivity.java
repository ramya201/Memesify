package edu.cmu.memesify;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends BaseActivity {
	private RelativeLayout memeLayout;
	private TextView topTextView;
	private TextView bottomTextView;
	private Bitmap memeImage;
	private Context context;
	private ShareActionProvider mShareActionProvider;
	private Bitmap myMeme;
	private File memeFile;
	private Spinner spinner;
	private HashMap<String,Integer> colorsMap = new HashMap<String,Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meme_edit);
		
		context = getApplicationContext();
		memeLayout = (RelativeLayout) findViewById(R.id.memeLayout);
		topTextView = (TextView) findViewById(R.id.topTextView);
		bottomTextView = (TextView) findViewById(R.id.bottomTextView);

		Intent intent = getIntent();
		int memeIndex = intent.getIntExtra("MemeIndex", -1);

		if (memeIndex != -1)
		{
			memeImage = meme_pics_editable.get(memeIndex);
			memeLayout.setBackground(new BitmapDrawable(getResources(), memeImage));

			String topText = defaultTopText.get(memeIndex);
			if (topText.equalsIgnoreCase(""))
			{
				topTextView.setHint(getResources().getString(R.string.top_hint));
			} else {
				topTextView.setText(topText);
			}
		}
		
		spinner = (Spinner) findViewById(R.id.color);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
         R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				topTextView.setTextColor(colorsMap.get(spinner.getItemAtPosition(arg2).toString()));
				bottomTextView.setTextColor(colorsMap.get(spinner.getItemAtPosition(arg2).toString()));				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

        });
        
        colorsMap.put("RED",Color.RED);
        colorsMap.put("BLACK",Color.BLACK);
        colorsMap.put("BLUE",Color.BLUE);
        colorsMap.put("GREEN",Color.GREEN);
        colorsMap.put("WHITE",Color.WHITE);
	}

	public void saveImage(View view)
	{
		String topText = topTextView.getText().toString();
		String bottomText = bottomTextView.getText().toString();

		Options bitmapOptions = new Options();
		bitmapOptions.inMutable = true;

		myMeme = memeImage.copy(memeImage.getConfig(), true);

		Canvas canvas = new Canvas(myMeme);
		canvas.setBitmap(myMeme);
		Paint paint = new Paint();
		paint.setColor(colorsMap.get(spinner.getSelectedItem().toString()));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(40.0f);
		
		if (bottomText.length()>20 && bottomText.length() < 35) {
			canvas.drawText(topText, 20, 50, paint);
		} else if (bottomText.length()>35) {
			paint.setTextSize(26.0f);
			canvas.drawText(topText, 20, 50, paint);			
		} else
			canvas.drawText(topText, 100, 50, paint);
		
		if (bottomText.length()>20 && bottomText.length() < 35) {
			canvas.drawText(bottomText, 20, memeImage.getHeight() - 50, paint);
		} else if (bottomText.length()>35) {
			paint.setTextSize(26.0f);
			canvas.drawText(bottomText, 20, memeImage.getHeight() - 50, paint);			
		} else
			canvas.drawText(bottomText, 100, memeImage.getHeight() - 50, paint);
		
		canvas.save();

		File dir = new File(Environment.getExternalStorageDirectory() + "/Memes");
		if (!dir.exists())
			dir.mkdir();

		Date date = new Date();
		CharSequence dateChars  = DateFormat.format("MM-dd-yy hh:mm:ss", date.getTime());

		try {
			memeFile = new File(dir,"Meme" + dateChars.toString() + ".jpg");
			myMeme.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(memeFile));
			Toast.makeText(context, "Meme saved", Toast.LENGTH_SHORT).show();
			
			if (memeFile != null)
			{
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("image/jpeg");
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(memeFile));
				setShareIntent(intent);
			} else
			{
				Toast.makeText(context, "Save meme first", Toast.LENGTH_SHORT).show();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);

		// Locate MenuItem with ShareActionProvider
		MenuItem item = menu.findItem(R.id.menu_item_share);

		// Fetch and store ShareActionProvider
		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		
		// Return true to display menu
		return true;
	}
	
	public void shareImage(View view) {
		
	}

	private void setShareIntent(Intent shareIntent) {
		if (mShareActionProvider != null) {
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}

}
