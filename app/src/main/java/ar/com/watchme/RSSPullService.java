package ar.com.watchme;

import android.app.IntentService;
import android.content.Intent;

public class RSSPullService extends IntentService {

    public RSSPullService() {
        super("RSSPullService");
    }

    public RSSPullService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        System.out.println("Proceso creado.");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        System.out.println(dataString);
    }
}
