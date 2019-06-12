package com.example.texsnapper.view;

import android.app.Application;
import android.graphics.Bitmap;

public class GlobalVars extends Application {
	private Bitmap image;

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
