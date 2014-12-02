package com.example.mobilewatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class Alarm extends Main{
	 private MediaPlayer mp;
	    private Float mp_vol;
	    private String mp_rng;
	    private SharedPreferences prefs;
	    private AnimationDrawable anim = null;
	    private final String DEFAULT_RNG = "rng_default";
	    private final String DEFAULT_VOL = "0.5";
	    private LayoutInflater inflater = null;
	    private SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
	        public void onSharedPreferenceChanged(SharedPreferences sharedPrefs,
	                String key) {
	            if (key.equals("volume")) {
	                setup();
	                if (mp.isPlaying()) {
	                    mp.setVolume(mp_vol, mp_vol);
	                } else {
	                    mp.release();
	                    loadClip();
	                }
	            } else if (key.equals("ring")) {
	                setup();
	                if (!mp.isPlaying()) {
	                    mp.release();
	                    loadClip();
	                }
	            }
	        }
	    };
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View btn = findViewById(R.id.start_alarm);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartAlarm();
            }
        });
        btn.setBackgroundResource(R.drawable.animation);
        anim = (AnimationDrawable) btn.getBackground();

        setup();
        loadClip();

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, Prefs.class));
            return true;
        }      return (super.onOptionsItemSelected(item));
    }

    private void StartAlarm() {
        if (mp.isPlaying()) {
            stop();
        } else {
            play();
        }
    }

    private void play() {
        anim.start();
        mp.start();
    }

    private void stop() {
        anim.stop();
        anim.selectDrawable(0);
        mp.stop();
        mp.release();
        loadClip();
    }

    private void setup() {
        try {
            mp_vol = Float.parseFloat(prefs.getString("volume", DEFAULT_VOL));
            mp_rng = prefs.getString("ring", DEFAULT_RNG);
        } catch (ClassCastException e) {
        }
    }

    private void loadClip() {
        try {
            mp = MediaPlayer.create(this, getResources().getIdentifier(mp_rng,
                    "raw", this.getPackageName()));
            mp.setVolume(mp_vol, mp_vol);
            mp.setLooping(true);
        } catch (Throwable t) {
        }
    }
}

