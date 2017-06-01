package com.example.baseadapter;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/* 
inflate()的作用就是将一个用xml定义的布局文件查找出来，注意与findViewById()的区别，inflate是加载一个布局文件，而findViewById则是从布局文件中查找一个控件。


1.获取LayoutInflater对象有三种方法

LayoutInflater inflater=LayoutInflater.from(this);
LayoutInflater inflater=getLayoutInflater();
LayoutInflater inflater=(LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);


2.关于LayoutInflater类inflate(int resource, ViewGroup root, boolean attachToRoot)方法三个参数的含义

resource：需要加载布局文件的id，意思是需要将这个布局文件中加载到Activity中来操作。

root：需要附加到resource资源文件的根控件，什么意思呢，就是inflate()会返回一个View对象，如果第三个参数attachToRoot为true，就将这个root作为根对象返回，否则仅仅将这个root对象的LayoutParams属性附加到resource对象的根布局对象上，也就是布局文件resource的最外层的View上，比如是一个LinearLayout或者其它的Layout对象。

attachToRoot：是否将root附加到布局文件的根视图上
 */

public  abstract class MyAdapter<T> extends BaseAdapter{
	
	
	private int mLayoutRes;
	private ArrayList<T> mData;
	
	public MyAdapter(){   
		
	}

	public MyAdapter(int mLayoutRes, ArrayList<T> linkedlist){
		this.mLayoutRes = mLayoutRes;
		this.mData = linkedlist;
	}
	
	
	@Override
	public int getCount() {
		return mData != null ?  mData.size() : 0;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public T getItem(int position) {
		return mData.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes, position);
		bindView(holder, getItem(position));		
		return holder.getItemView();
	}
	
	
	public static class ViewHolder{
		private SparseArray<View> mViews;
		private View item;
		private int position;
		private Context context;
		
		private ViewHolder(Context context, ViewGroup parent, int layoutRes){
			mViews = new SparseArray<>();
			this.context = context;
			View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
			convertView.setTag(this);
			item = convertView;
		}
		
		public static ViewHolder bind(Context context, View convertView, ViewGroup parent, int layoutRes, int position){
			ViewHolder holder;
			if(convertView == null){
				holder = new ViewHolder(context, parent, layoutRes);
			}else{
				holder = (ViewHolder) convertView.getTag();
				holder.item = convertView;
			}
			holder.position = position;
			return holder;
		}
		
		public <T extends View> T getView(int id){
			T t = (T) mViews.get(id);
			if(t == null){
				t = (T) item.findViewById(id);
				mViews.put(id, t);
			}
			return t;
		}
		
		public View getItemView(){
			return item;
		}
		
		public int getItemPosition(){
			return position;
		}
		
		public ViewHolder setText(int id, CharSequence text){
			View view = getView(id);
			if(view instanceof TextView){
				((TextView) view).setText(text);
			}
			return this;
		}
		
		public ViewHolder setImageResource(int id, int drawableRes){
			View view = getView(id);
			if(view instanceof ImageView){
				((ImageView) view).setImageResource(drawableRes);
			}else{
				view.setBackgroundResource(drawableRes);;
			}
			
			return this;
		}
	}

	public abstract void bindView(ViewHolder holder, T obj);
}
