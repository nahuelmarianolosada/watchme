package ar.com.watchme;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Chronometer;

public class MyChronometer extends Chronometer {
    private boolean isRunning = false;
    private String format = "Time Running - %s";


    public MyChronometer(Context context) {
        super(context);
        super.setFormat(format);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void start() {
        super.start();
        isRunning = true;
    }


    public void start(Button btn) {
        this.start();
        btn.setText("Stop");
    }


    @Override
    public void stop() {
        super.stop();
        isRunning = false;
    }


    public void stop(Button btn) {
        this.stop();
        btn.setText("Restart");
    }


    public void restart(){
        super.setBase(SystemClock.elapsedRealtime());
        start();
    }


    public void restart(Button btn){
        this.restart();
        btn.setText("Stop");
    }


    public boolean isRunning() {
        return isRunning;
    }

}
