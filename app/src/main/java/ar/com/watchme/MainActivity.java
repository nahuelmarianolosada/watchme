package ar.com.watchme;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    long lastClickTime;
    TextView timeText;
    Button fightButton;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
    MyChronometer myChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fightButton = (Button) findViewById(R.id.fightButton);
        timeText = (TextView) findViewById(R.id.timeText);
        lastClickTime = SystemClock.elapsedRealtime();
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAndShowTime();
            }
        });

        myChronometer = findViewById(R.id.simpleChronometer); // initiate a chronometer
        myChronometer.start();

        Intent intent = new Intent(this, RSSPullService.class);
        this.startService(intent);
        System.out.println("Main Activity creado!");
    }



    protected void stopRestartChronometer(View v){
        myChronometer = findViewById(R.id.simpleChronometer); // initiate a chronometer
        Button button_stopRestart = (Button) findViewById(R.id.stopButton);
        if(myChronometer.isRunning()){
            myChronometer.stop(button_stopRestart);
        }else if(button_stopRestart.getText().equals("Restart")) {
            myChronometer.restart(button_stopRestart);
        }
    }


    protected void stopAndShowTime(){
        Long timeNow = SystemClock.elapsedRealtime();
        Long timeElapsed = SystemClock.elapsedRealtime() - lastClickTime;
        lastClickTime = timeNow;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timeElapsed));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        timeText.setText("Tiempo desde el ultimo fight: " +  sdf.format(cal.getTime()));
    }





    /*@Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_POWER) {
            stopAndShowTime();
            event.startTracking(); // Needed to track long presses
            return true;
        }
        return super.onKeyDown(keyCode, event);
        *//*return false;*//*
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
            stopAndShowTime();
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
