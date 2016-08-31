package com.ITXY.weixin.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import com.ITXY.weixin.MyApplication;
import com.ITXY.weixin.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyUserInfoActivity extends Activity {
	    private RelativeLayout re_avatar;
	    private RelativeLayout re_name;
	    private RelativeLayout re_fxid;
	    private RelativeLayout re_sex;
	    private RelativeLayout re_region;
        private RelativeLayout re_erweima;
	    private ImageView iv_avatar;
	    private TextView tv_name;
	    private TextView tv_fxid;
	    private TextView tv_sex;
	    private TextView tv_sign;
	  
	      static String wiId="";
	    private static final int BACK=4;
	    private static final int TAKE_PHOTO = 1;// 拍照
	    private static final int CROP_PHOTO = 2;// 启动剪裁程序
	    private static final int CHOOSE_PHOTO = 3;// 从相册中选取头像
	  
	   
	    String hxid;
	    String fxid;
	    String sex;
	    String sign;
	    String nick;
		private Uri imageUri;
		
	    @SuppressLint("SdCardPath")
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_myinfo);
	        initView();

	    }

	    private void initView() {
	        hxid = LocalUserInfo.getInstance(MyUserInfoActivity.this).getUserInfo(
	                "hxid");
	        nick = LocalUserInfo.getInstance(MyUserInfoActivity.this).getUserInfo(
	                "nick");
	        fxid = LocalUserInfo.getInstance(MyUserInfoActivity.this).getUserInfo(
	                "fxid");
	        sex = LocalUserInfo.getInstance(MyUserInfoActivity.this).getUserInfo(
	                "sex");
	        sign = LocalUserInfo.getInstance(MyUserInfoActivity.this).getUserInfo(
	                "sign");
	       
            re_erweima=(RelativeLayout) this.findViewById(R.id.re_erweima);
	        re_avatar = (RelativeLayout) this.findViewById(R.id.re_avatar);
	        re_name = (RelativeLayout) this.findViewById(R.id.re_name);
	        re_fxid = (RelativeLayout) this.findViewById(R.id.re_fxid);
	        re_sex = (RelativeLayout) this.findViewById(R.id.re_sex);
	        re_region = (RelativeLayout) this.findViewById(R.id.re_region);
	        re_avatar.setOnClickListener(new MyListener());
	        re_name.setOnClickListener(new MyListener());
	        re_fxid.setOnClickListener(new MyListener());
	        re_sex.setOnClickListener(new MyListener());
	        re_region.setOnClickListener(new MyListener());
	        re_erweima.setOnClickListener(new MyListener());
	        // 头像
	        iv_avatar = (ImageView) this.findViewById(R.id.iv_avatar);
	        tv_name = (TextView) this.findViewById(R.id.tv_name);
	        tv_fxid = (TextView) this.findViewById(R.id.tv_fxid);
	        tv_sex = (TextView) this.findViewById(R.id.tv_sex);
	        tv_sign = (TextView) this.findViewById(R.id.tv_sign);
	        tv_name.setText(nick);
	        if (sex.equals("1")) {
	            tv_sex.setText("男");

	        } else if (sex.equals("2")) {
	            tv_sex.setText("女");

	        } else {
	            tv_sex.setText("");
	        }

	     
	        
	    }

	    class MyListener implements OnClickListener {

	        @Override
	        public void onClick(View v) {
	            switch (v.getId()) {
	            case R.id.re_avatar:
	                showPhotoDialog();
	                break;
	            case R.id.re_sex:
	                showSexDialog();
	                break;
	            case R.id.re_fxid:
	            	Intent intent =new Intent(MyApplication.getContext(),WxIdActivity.class);
	            	startActivityForResult(intent, BACK);
	            case R.id.re_erweima:
	            	
	            	Intent intent1=new Intent(MyApplication.getContext(),eCodePictureActivity.class);
	            	intent1.putExtra("wiId",wiId);
	            	startActivity(intent1);
	            }
	        }
	    }
	    
	    
	    private void showPhotoDialog() {
	        final AlertDialog dlg = new AlertDialog.Builder(this).create();
	        dlg.show();
	        Window window = dlg.getWindow();
	        // *** 主要就是在这里实现这种效果的.
	        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
	        window.setContentView(R.layout.alertdialog);
	        // 为确认按钮添加事件,执行退出应用操作
	        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
	        tv_paizhao.setText("拍照");
	        tv_paizhao.setOnClickListener(new View.OnClickListener() {
	            @SuppressLint("SdCardPath")
	            public void onClick(View v) {

	            	File outputImage = new File(Environment.getExternalStorageDirectory(),
							"output_image.jpg");
					try {
						if (outputImage.exists()) {
							outputImage.delete();
						}
						outputImage.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					imageUri = Uri.fromFile(outputImage);
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, TAKE_PHOTO);
				}
			});
			
	        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
	        tv_xiangce.setText("相册");
	        tv_xiangce.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	Intent intent=new Intent("android.intent.action.GET_CONTENT");
	            	intent.setType("image/*");
	            	startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
	            }
	            	
	            });

	            	
	    }

	    private void showSexDialog() {
	        final AlertDialog dlg = new AlertDialog.Builder(this).create();
	        dlg.show();
	        Window window = dlg.getWindow();
	        // *** 主要就是在这里实现这种效果的.
	        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
	        window.setContentView(R.layout.alertdialog);
	        LinearLayout ll_title = (LinearLayout) window
	                .findViewById(R.id.ll_title);
	        ll_title.setVisibility(View.VISIBLE);
	        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
	        tv_title.setText("性别");
	        // 为确认按钮添加事件,执行退出应用操作
	        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
	        tv_paizhao.setText("男");
	        tv_paizhao.setOnClickListener(new View.OnClickListener() {
	            @SuppressLint("SdCardPath")
	            public void onClick(View v) {
	                if (!sex.equals("1")) {
	                    tv_sex.setText("男");
	                    updateSex("1");
	                }

	                dlg.cancel();
	            }
	        });
	        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
	        tv_xiangce.setText("女");
	        tv_xiangce.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {

	                if (!sex.equals("2")) {
	                    tv_sex.setText("女");
	                    updateSex("2");
	                }

	                dlg.cancel();
	            }
	        });

	    }

	 
	    public void back(View view) {
	        finish();
	    }

	    public void updateSex(final String sexnum) {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("hxid", hxid);
	        map.put("sex", sexnum);
	       
	    }
	    @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			
			switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					Intent intent = new Intent("com.android.camera.action.CROP");
					intent.setDataAndType(imageUri, "image/*");
					intent.putExtra("scale", true);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, CROP_PHOTO);
				}
				break;
			case CROP_PHOTO:
				if (resultCode == RESULT_OK) {
					try {
						Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
								.openInputStream(imageUri));
						iv_avatar.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			case CHOOSE_PHOTO:
				if(resultCode==RESULT_OK){
//					判断手机版本号
					if(Build.VERSION.SDK_INT>=19){
						handleImageOnKitKat(data);
					}else{
						handleImageBeforeKitKat(data);
					}
					
				}
			case BACK:
				String wxId=data.getStringExtra("wxId");
				wiId=wxId;
				tv_fxid.setText(wxId);
			
				
			default:
				break;
			}
		}

		private void handleImageBeforeKitKat(Intent data) {
			// TODO Auto-generated method stub
			Uri uri=data.getData();
			String imagePath=getImagePath(uri, null);
			displayImage(imagePath);
		}

		private void handleImageOnKitKat(Intent data) {
			// TODO Auto-generated method stub
			String imagePath=null;
			Uri uri=data.getData();
			if(DocumentsContract.isDocumentUri(this, uri)){
				String docId=DocumentsContract.getDocumentId(uri);
				if("com.android.providers.media.documents".equals(uri.getAuthority())){
					String id=docId.split(":")[1];
					String selection =MediaStore.Images.Media._ID+"="+id;
					imagePath=getImagePath(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);}
				else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
					Uri contentUri=ContentUris.withAppendedId(uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
					imagePath=getImagePath(contentUri,null);
					
				}
				}else if("content".equalsIgnoreCase(uri.getScheme())){
					imagePath=getImagePath(uri, null);
				}
			displayImage(imagePath);
			}

		private void displayImage(String imagePath) {
			// TODO Auto-generated method stub
			if(imagePath!=null){
				Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
				iv_avatar.setImageBitmap(bitmap);
			}else {
				Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
			}
			
		}

		private String getImagePath(Uri uri, String selection) {
			// TODO Auto-generated method stub
			String path=null;
			Cursor cursor=getContentResolver().query(uri, null, selection, null, null);
			if(cursor!=null){
				if(cursor.moveToFirst()){
					path=cursor.getString(cursor.getColumnIndex(Media.DATA));
					
				}cursor.close();
			}
			return path;
			
		}
	
		}

	


	
	
	

