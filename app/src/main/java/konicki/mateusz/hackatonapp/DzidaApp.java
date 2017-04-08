package konicki.mateusz.hackatonapp;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.annimon.stream.Stream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import konicki.mateusz.hackatonapp.api.ApiService;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.manager.BeaconManager;
import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.model.BeaconType;
import konicki.mateusz.hackatonapp.model.Place;
import konicki.mateusz.hackatonapp.model.Queue;
import konicki.mateusz.hackatonapp.paymentservice.PaymentService;
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
        Intent payment = new Intent(this, PaymentService.class);
        this.startService(payment);
    }

}
