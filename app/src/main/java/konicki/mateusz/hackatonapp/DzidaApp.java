package konicki.mateusz.hackatonapp;

import android.app.Application;
import android.content.Intent;

import konicki.mateusz.hackatonapp.manager.BeaconManager;
import konicki.mateusz.hackatonapp.service.BluetoothConnectionlessService;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class DzidaApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BeaconManager.getInstance(this);
        Intent intent = new Intent(this, BluetoothConnectionlessService.class);
        this.startService(intent);
    }

}
