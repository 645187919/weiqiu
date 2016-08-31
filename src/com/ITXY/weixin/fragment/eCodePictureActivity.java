package com.ITXY.weixin.fragment;

import com.ITXY.weixin.MyApplication;
import com.ITXY.weixin.R;
import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class eCodePictureActivity extends Activity {
	private ImageView erweimaPic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erweima);
		erweimaPic=(ImageView) findViewById(R.id.erweima);
		Intent intent5=getIntent();
		String wxId=intent5.getStringExtra("wiId");
		if(wxId==null){
			Toast.makeText(MyApplication.getContext(), "«Î…Ë÷√ƒ„µƒŒ¢«Ú’À∫≈", Toast.LENGTH_SHORT).show();
		}
		else{
			try {
				Bitmap erweimaPicture=EncodingHandler.createQRCode(wxId, 500);
				erweimaPic.setImageBitmap(erweimaPicture);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
