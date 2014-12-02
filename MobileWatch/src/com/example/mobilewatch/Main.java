package com.example.mobilewatch;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class Main extends Activity {
Button b_alarm,b_report,b_speech,b_eme;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		//Button button1 = (Button) findViewById(R.id.button1);
		Button b_alarm = (Button) findViewById(R.id.b_alarm);
		Button b_report = (Button) findViewById(R.id.b_report);
		Button b_speech = (Button) findViewById(R.id.b_speech);
		Button b_eme = (Button) findViewById(R.id.b_eme);

		b_alarm.setOnClickListener(new View.OnClickListener(){
			public void onClick(View s){
			startActivity(new Intent(Main.this,Alarm.class));
		}
	});

		b_report.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View s){
			startActivity(new Intent(Main.this,SendEmailActivity.class));
			}
		});
		
		b_speech.setOnClickListener(new View.OnClickListener()
				{
			public void onClick(View s){
				startActivity(new Intent(Main.this,MainActivity.class));
			}
			});
		
		b_eme.setOnClickListener(new View.OnClickListener()
		{
	public void onClick(View s){
		startActivity(new Intent(Main.this,Eme_MainActivity.class));
	}
	});
	}
}
