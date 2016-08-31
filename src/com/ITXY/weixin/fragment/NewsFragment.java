package com.ITXY.weixin.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ITXY.weixin.ChatActivity;
import com.ITXY.weixin.MainActivity;
import com.ITXY.weixin.MyApplication;
import com.ITXY.weixin.News;
import com.ITXY.weixin.NewsAdapter;
import com.ITXY.weixin.R;
import com.ITXY.weixin.R.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsFragment extends Fragment implements OnItemClickListener{
	private ListView mlistView;
	private List<News> newsList=new ArrayList<News>();
	private NewsAdapter mAdapter;
	private MainActivity parentActivity;
	@Override 
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		newsList=initNews();
		mAdapter=new NewsAdapter(context, R.layout.news_item, newsList);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= inflater.inflate(R.layout.tab01, container, false);
		mlistView=(ListView) view.findViewById(R.id.weiqiu_listView);
		mlistView.setAdapter(mAdapter);
		mlistView.setOnItemClickListener(this);
		return view;
	}
	private List<News> initNews() {
		// TODO Auto-generated method stub
		News new1=new News("订阅号", "我到拍摄的骄傲是看你发榴弹法拉伐",R.drawable.photo8);
		newsList.add(new1);
		News new2=new News("小Q", "[语音聊天]",R.drawable.photo1);
		newsList.add(new2);
		News new3=new News("ZZ", "1456156156",R.drawable.photo2);
		newsList.add(new3);
		News new4=new News("ASD", "我通过了你的好友邀请，现在我们可以聊天了",R.drawable.photo3);
		newsList.add(new4);
		News new5=new News("FAF", "[435]裴斐[小视频]",R.drawable.photo4);
		newsList.add(new5);
		News new6=new News("阿里巴巴招聘", "工作半年了，该告诉你一些真相了",R.drawable.photo5);
		newsList.add(new6);
		return newsList;
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		News news=newsList.get(position);
		if(news.getTitle()=="ZZ"){
			Intent intent=new Intent(MyApplication.getContext(),ChatActivity.class);
			startActivity(intent);
			
		}
		
	}


}
