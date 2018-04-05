package ar.com.watchme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartAtBootServiceReceiver extends BroadcastReceiver {
    static boolean wasScreenOn;
    private boolean screenOff;

    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
            wasScreenOn = false;
            Toast.makeText(context, "Power Off", Toast.LENGTH_SHORT).show();
        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
            wasScreenOn = true;
        }
        Intent i = new Intent(context, SampleService.class);
        i.putExtra("screen_state", screenOff);
        i.setAction("com.example.antitheft.SampleService");
        context.startService(i);
//
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i1 = new Intent();
            i1.setAction("com.example.sampleonkeylistener.MainActivity");
            context.startService(i1);
        }
    }
}
