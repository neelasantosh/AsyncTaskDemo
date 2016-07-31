package com.example.asyntaskapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	EditText editA, editB;
	Button buttonpower;
	TextView textResult;
	ProgressBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		editA = (EditText) findViewById(R.id.editText1);
		editB = (EditText) findViewById(R.id.editText2);
		textResult = (TextView) findViewById(R.id.textView1);
		buttonpower = (Button) findViewById(R.id.button1);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setVisibility(View.INVISIBLE);
		buttonpower.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str1 = editA.getText().toString();
				String str2 = editB.getText().toString();
				int a = Integer.parseInt(str1);
				int b = Integer.parseInt(str2);

				// use Asyn Task here//replaces Thread
				PowerTask task = new PowerTask();
				task.execute(a, b);// replaces thread.start();a and b passed
									// here will be received by params in
									// doInBackground() method

			}
		});

	}// eof onCreate

	// Async task is generic class,<Integer,Integer,Integer> are starting
	// input,middle value generated and String final value
	class PowerTask extends AsyncTask<Integer, Integer, String> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progress.setVisibility(View.VISIBLE);
		}
		
		
		
		@Override
		protected String doInBackground(Integer... params) {
			
			int a = params[0];
			int b = params[1];
			int power = 1;
			for (int i = 1; i <= b; i++) {
				power = power * a; // (i,power)
				publishProgress(i, power);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
			}
			return "Thank You";
		}//eof doInBackground()
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int i = values[0];
			int power = values[1];
			textResult.setText(i + " :" + power);
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(HomeActivity.this, result, 5).show();
			progress.setVisibility(View.INVISIBLE);
		}

	}

}// eof activity
