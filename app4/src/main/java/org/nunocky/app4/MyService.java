package org.nunocky.app4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class MyService extends Service {
    private static final String TAG = MyService.class.getCanonicalName();

    private Handler mHandler = new Handler();

    class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private LocalBinder mBinder = new LocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService:onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MyService:onUnbind");
        return super.onUnbind(intent);
    }


    // ========================================================================
    private String mTimeString;
    private boolean mActive;

    public void start() {
        mActive = true;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                mTimeString = date.toString();

                Intent intent = new Intent("myservice_updated");
                intent.putExtra("value", mTimeString);
                sendBroadcast(intent);

                if (mActive) {
                    mHandler.postDelayed(this, 1000);
                }
            }
        });
    }

    public void stop() {
        mActive = false;
    }

    public String getValue() {
        return mTimeString;
    }
}
