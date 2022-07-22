package com.rmxp4droid;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FileChooser extends ListActivity implements
		FileFilter, View.OnClickListener, View.OnKeyListener {

	private final File sdcardDir = Environment
			.getExternalStorageDirectory();
	private File currentDir;
	private String[] filters=new String[]{".ini"};
	private EditText pathEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.file_chooser);
		getListView().setEmptyView(findViewById(R.id.empty));

		pathEdit = (EditText) findViewById(R.id.path);
		pathEdit.setOnKeyListener(this);

		findViewById(R.id.goto_sdcard).setOnClickListener(this);
		findViewById(R.id.goto_parent).setOnClickListener(this);



		String path = null;
		if (savedInstanceState != null)
			path = savedInstanceState.getString("currentDir");
		else
			path = getInitialPath();

		File dir = null;
		if (path != null)
			dir = getDirectoryFromFile(path);
		if (dir == null)
			dir = sdcardDir;
		changeTo(dir);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		getMenuInflater().inflate(R.menu.file_chooser, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			changeTo(currentDir);
			return true;
 

		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (currentDir != null)
			outState.putString("currentDir", currentDir.getAbsolutePath());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String name = l.getItemAtPosition(position).toString();
		File f = new File(currentDir, name);
		if (f.isDirectory())
			changeTo(f);
		else
			onFileSelected(f);
	}


	protected String getInitialPath() {
		Uri uri = getIntent().getData();
		if (uri == null)
			return null;
		return uri.getPath();
	}

	protected void onFileSelected(File file) {
		
		BaseConf.WORK_FOLDER=file.getParentFile().getPath().replaceAll("\\\\","/")+"/";
			Intent intent = new Intent();
			intent.setClass(this, LoadingActivity.class);
			startActivity(intent);
			finish();

	}

	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_ENTER) {
			String name = pathEdit.getText().toString().trim();
			if (name.length() > 0) {
				File dir = new File(name);
				if (dir.isDirectory())
					changeTo(dir);
				
				return true;
			}
		}
		return false;
	}

	public void onClick(View v) {
		switch (v.getId()) {


		case R.id.goto_sdcard:
			changeTo(sdcardDir);
			break;

		case R.id.goto_parent:
			File parent = currentDir.getParentFile();
			if (parent != null)
				changeTo(parent);
			break;
		}
	}

	public boolean accept(File file) {
		String name = file.getName();

		// Do not show hidden files
		if (name.startsWith("."))
			return false;

		// Always show directory
		if (file.isDirectory())
			return true;
 
		name = name.toLowerCase(Locale.US);
		for (String f : filters) {
			if (name.endsWith(f))
				return true;
		}
		return false;
	}

	private File getDirectoryFromFile(String path) {
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir = dir.getParentFile();
			if (dir != null && !dir.isDirectory())
				dir = null;
		}
		return dir;
	}

	private void changeTo(File dir) {
		File[] files = dir.listFiles(filters == null ? null : this);
		if (files == null)
			files = new File[0];

		currentDir = dir;
		pathEdit.setText(dir.getAbsolutePath());

		List<String> items = new ArrayList<String>(files.length);
		for (File f : files) {
			String name = f.getName();
			if (f.isDirectory())
				name += '/';
			items.add(name);
		}

		Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
		setListAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, items));
	}


}
