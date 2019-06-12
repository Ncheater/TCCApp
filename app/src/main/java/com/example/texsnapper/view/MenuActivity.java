package com.example.texsnapper.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.texsnapper.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

	private GlobalVars gv = (GlobalVars) getApplication();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageButton imgB = findViewById(R.id.newImgButton);
		ImageButton galB = findViewById(R.id.galleryButton);
		ImageButton confB = findViewById(R.id.configsButton);
		ImageButton exitB = findViewById(R.id.exitButton);

		imgB.setOnClickListener(this);
		galB.setOnClickListener(this);
		confB.setOnClickListener(this);
		exitB.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ImageButton b = (ImageButton) v;

		switch (b.getId()) {
			case R.id.newImgButton:
				startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 1);
				break;
			case R.id.galleryButton:
				Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				gallery.setType("image/*");
				gallery.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(gallery, "Selecione uma imagem"), 1);
				break;
			case R.id.configsButton:
				//TODO
				break;
			case R.id.exitButton:
				super.finish();
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		assert data != null && data.getExtras() != null;
		switch (requestCode) {
			case 1:
				if (resultCode == Activity.RESULT_OK) {
					doCrop((Uri) data.getExtras().getParcelable("data"));
				}
				break;
			case 2:
				if (resultCode == Activity.RESULT_OK) {
					gv.setImage((Bitmap) data.getExtras().getParcelable("data"));
					startActivity(new Intent(MenuActivity.this, DiffuseActivity.class));
				}
				break;
		}
	}

	private void doCrop(Uri picUri) {
		try {
			Intent crop = new Intent("com.android.camera.action.CROP");
			crop.setDataAndType(picUri, "image/*");
			crop.putExtra("crop", true);
			crop.putExtra("aspectX", 1);
			crop.putExtra("aspectY", 1);
			crop.putExtra("return-data", true);
			startActivityForResult(crop, 2);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "Seu dispositivo não suporta recortar imagens, a imagem será ajustada a partir do centro.", Toast.LENGTH_LONG).show();
		}
	}
}
