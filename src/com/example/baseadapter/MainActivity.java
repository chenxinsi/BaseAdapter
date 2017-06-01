package com.example.baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

	private ListView listview;
	private MyAdapter<Book> bookAdapter;
	private List<Book> books;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listview = (ListView) findViewById(R.id.listview);
		books = new ArrayList<Book>();
		books.add(new Book("book1","content1"));
		books.add(new Book("book2","content2"));
		books.add(new Book("book3","content3"));
		bookAdapter = new MyAdapter<Book>(R.layout.listview_item,(ArrayList)books){
			public void bindView(MyAdapter.ViewHolder holder, Book obj) {
				holder.setText(R.id.listview_tv1, obj.getbName());
				holder.setText(R.id.listview_tv2, obj.getbAuthor());
			}
		};
		listview.setAdapter(bookAdapter);
	}

}
