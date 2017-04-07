package konicki.mateusz.hackatonapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Mateusz on 07.04.2017.
 */
public abstract class ViewServiceBase extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public abstract IBinder onBind(Intent intent);
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public abstract boolean onResume();
    public abstract void initialize();
}