package com.example.mobilewatch;

import java.util.ArrayList;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Main {
	
	protected static final int RESULT_SPEECH = 1;
	private ImageButton btnSpeak;
    public EditText see_text;
    ArrayList<String> text ;
    Button send;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speech);
		see_text = (EditText) findViewById(R.id.see_text);
		//number = (EditText) findViewById(R.id.number);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

		send = (Button) findViewById(R.id.send);
		btnSpeak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
		
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					SmsManager smsManager = SmsManager.getDefault();
					String num1;
					num1="9866708305";
					String sms=see_text.getText().toString();
					smsManager.sendTextMessage(num1, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
								Toast.LENGTH_LONG).show();
				  } catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				  }
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {
				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				//txtText.setText("text iz:"+text.get(0));
				see_text.setText(text.get(0));
			}
			break;
		}
		}
}
}