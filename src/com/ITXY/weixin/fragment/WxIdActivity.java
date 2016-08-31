package com.ITXY.weixin.fragment;

import com.ITXY.weixin.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class WxIdActivity extends Activity {
	private EditText wxIdEdit;
	private Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wxid);
		wxIdEdit=(EditText) findViewById(R.id.editext);
		save=(Button) findViewById(R.id.Wx_id);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2=new Intent();
			    String qCode=wxIdEdit.getText().toString();
				intent2.putExtra("wxId", qCode);
				setResult(RESULT_OK, intent2);
				finish();
			}
		});
		
	}

}
