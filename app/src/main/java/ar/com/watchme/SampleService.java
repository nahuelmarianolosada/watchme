package ar.com.watchme;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.Handler;

public class SampleService extends Service
{

    ContentObserver mSettingsContentObserver;
    AudioManager mAudioManager;
    private ComponentName mRemoteControlResponder;
    private Intent intent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.v("StartServiceAtBoot", "StartAtBootService -- onStartCommand()");
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        System.out.println("Screen on=" + screenOn);
        if (!screenOn) {
            Toast.makeText(getApplicationContext(), "On", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Off", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCreate()
    {
        /*mSettingsContentObserver = new SettingContentObserver(this,new Handler());*/
        getApplicationContext().getContentResolver().registerContentObserver
                (android.provider.Settings.System.CONTENT_URI, true, mSettingsContentObserver );
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mRemoteControlResponder = new ComponentName(getPackageName(),
                StartAtBootServiceReceiver.class.getName());

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new StartAtBootServiceReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public void onDestroy()
    {
        getApplicationContext().getContentResolver().unregisterContentObserver(mSettingsContentObserver);
    }




}