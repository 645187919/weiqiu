package com.ITXY.weixin;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
	private int resourId;


	public NewsAdapter(Context context, int resource, List<News> objects) {
		super(context, resource, objects);
		resourId=resource;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		News news=getItem(position);
		View view;
		ViewHolder holder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourId, null);
			holder=new ViewHolder();
			holder.pic=(ImageView) view.findViewById(R.id.item_img);
			holder.title=(TextView) view.findViewById(R.id.item_title);
			holder.content=(TextView) view.findViewById(R.id.item_content);
			view.setTag(holder);

		}
		else{
			view=convertView;
			holder=(ViewHolder) view.getTag();
			
		}
		holder.pic.setImageResource(news.getImageId());
		holder.title.setText(news.getTitle());
		holder.content.setText(news.getContent());
		return view;
		
	}
	    class ViewHolder{
		 ImageView pic;
		 TextView  title;
		 TextView  content;
		
	}
}
