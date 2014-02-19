package com.vjaya.sampleec;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DrawImage mDi = new DrawImage(getApplicationContext());
		setContentView(mDi);
	}

}
