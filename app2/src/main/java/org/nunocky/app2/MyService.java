package org.nunocky.app2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = MyService.class.getCanonicalName();

    class LocalBinder extends Binder {

        MyService getService() {
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


    // =====================================================================================
    private int mValue;

    public void increment() {
        mValue++;
    }

    public void decrement() {
        mValue--;
    }

    public int getValue() {
        return mValue;
    }
}
