package com.example.mobilewatch;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SendEmailActivity extends Main{
	Button b_road,get_loc,b_water,b_light,b_health;
	//EditText textTo;
	String to;
	//EditText textSubject;
	String subject;
	EditText msg_body;
	//EditText current_loc;
	String l;
	String type=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_layout);
		b_road = (Button) findViewById(R.id.b_road);
		get_loc = (Button) findViewById(R.id.b_loc);
		b_water=(Button) findViewById(R.id.b_water);
		b_light=(Button) findViewById(R.id.b_light);
		b_health=(Button) findViewById(R.id.b_health);
		
		msg_body = (EditText) findViewById(R.id.editText_body);
		get_loc.setOnClickListener(new OnClickListener(){
			public void onClick(View vv)
			{
	                GPSTracker gps;
			        gps = new GPSTracker(SendEmailActivity.this);
			    	String lat=null;
					String lon=null;
					// check if GPS enabled		
			        if(gps.canGetLocation()){
						lat=String.valueOf(gps.getLatitude()); // returns latitude
						lon=String.valueOf(gps.getLongitude()); // returns longitude
						//type=gps.getType();
			        l= "Thee Location is - \nLat: " + lat + "\nLong: " + lon;
			        msg_body.append(l.toString());
		            //current_loc.setText(l.toString());
			        }else{
			        	gps.showSettingsAlert();
			        }
			  }
		});
		b_road.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
			 // String to = textTo.getText().toString();
			  to="owais.aws@gmail.com";
			 // String subject = textSubject.getText().toString();
			  subject="Reporting about bad road";
			  String message = msg_body.getText().toString();
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
			  //need this to prompts email client only
			  email.setType("message/rfc822");
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
		});
		
		b_water.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			  to="owais.aws@gmail.com";
			  subject="Reporting about bad water";
			  String message = msg_body.getText().toString();
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
			  email.setType("message/rfc822");
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
		});
		
		b_light.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			  to="owais.aws@gmail.com";
			  subject="Reporting about light";
			  String message = msg_body.getText().toString();
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
			  email.setType("message/rfc822");
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
		});
		
		b_health.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			  to="owais.aws@gmail.com";
			  subject="Reporting about health";
			  String message = msg_body.getText().toString();
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
			  email.setType("message/rfc822");
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			  
			}
		});
}
}