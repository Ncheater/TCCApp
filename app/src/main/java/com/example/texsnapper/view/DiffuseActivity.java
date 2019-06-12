package com.example.texsnapper.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.example.texsnapper.R;

public class DiffuseActivity extends AppCompatActivity {

	private GlobalVars gv = (GlobalVars) getApplication();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diffuse);
		ImageView preview = findViewById(R.id.preview);
		preview.setImageBitmap(gv.getImage());
	}
}
