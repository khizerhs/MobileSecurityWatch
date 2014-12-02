package com.example.mobilewatch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class Change extends Activity{
	String num1=null;
	String num2=null;
	String num3=null;
	String sm=null;
	Button b;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change);
		b=(Button) findViewById(R.id.save);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				EditText tv1=(EditText) findViewById(R.id.editText1);
		    	num1=tv1.getText().toString();
		         
		    	EditText tv2=(EditText) findViewById(R.id.editText2);
		    	num2=tv2.getText().toString();
		    	
		    //	EditText tv3=(EditText) findViewById(R.id.editText3);
		    	///num3=tv3.getText().toString();

		    	EditText tv4=(EditText) findViewById(R.id.editText4);
		    	sm=tv4.getText().toString();
		    	
		    	SharedPreferences pref=getSharedPreferences("MYPREF", 0);
			    final SharedPreferences.Editor editor=pref.edit();
			    editor.putString("num1",num1);
			   	editor.putString("num2",num2);
			   	editor.putString("num3",num3);
			   	editor.putString("sm",sm);
			   	
		    	CheckBox cb=(CheckBox) findViewById(R.id.checkBox1);
		    	cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							editor.putInt("over",1);
						}
						
					}
				});
		    	
		    	
		    
		   	editor.commit();
		   	Toast.makeText(Change.this,"Saved the Settings",Toast.LENGTH_SHORT).show();
			}
		});
		
    	
   	 
	}
}
