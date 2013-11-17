package edu.cmu.memesify;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivity extends BaseActivity {
	
	private ListView memeList;
	private CustomListAdapter adapter;
	private Context context;
	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meme_list);

		context = getApplicationContext();
		memeList = (ListView) findViewById(R.id.memeList);

		adapter = new CustomListAdapter(context, meme_titles, meme_pics);
		memeList.setAdapter(adapter);

		memeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, DetailsActivity.class);
				intent.putExtra("MemeIndex", arg2);
				startActivity(intent);
			} 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		MenuItem searchViewItem = menu.findItem(R.id.menu_search);
		searchView = (SearchView) searchViewItem.getActionView();
		
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
			@Override
			public boolean onQueryTextChange( String newText ) {
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				
				return true;		        
			}
		});
		return true;
	}

}
