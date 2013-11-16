package edu.cmu.memesify;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import android.os.Bundle;
import android.os.Environment;
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
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
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
		paint.setColor(Color.WHITE);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(40.0f);
		canvas.drawText(topText, 100, 50, paint);
		canvas.drawText(bottomText, 100, memeImage.getHeight() - 50, paint);
		canvas.save();
				
		File dir = new File(Environment.getExternalStorageDirectory() + "/Memes");
		if (!dir.exists())
			dir.mkdir();
		
		Date date = new Date();
		CharSequence dateChars  = DateFormat.format("MM-dd-yy hh:mm:ss", date.getTime());
		
		try {
			myMeme.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(dir,"Meme" + dateChars.toString() + ".jpg")));
			Toast.makeText(context, "Meme saved", Toast.LENGTH_SHORT).show();
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
	    View shareView = item.getActionView();
	    
	    shareView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				
			}
	    	
	    });

	    // Return true to display menu
	    return true;
	}
	
	private void setShareIntent(Intent shareIntent) {
	    if (mShareActionProvider != null) {
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	}

}
