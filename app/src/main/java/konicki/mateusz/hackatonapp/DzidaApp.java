package konicki.mateusz.hackatonapp;

import android.app.Application;
import android.content.Intent;

import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.manager.BeaconManager;
import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.model.Place;
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
        DBHelper dbHelper = new DBHelper(this);
        Beacon beacon = new Beacon();
        beacon.setMac("7C:2F:80:8E:9F:00");
        beacon.setX(0.35f);
        beacon.setY(0.17f);
        beacon.setMapId(11);
        Place place = new Place();
        place.setName("Chuj");
        place.setDescription("scscscasadsadasdasdasdasdasdasdasdad");
        place.setUrl("https://static.mgmresorts.com/content/dam/MGM/new-york-new-york/entertainment/attractions/the-roller-coaster-at-new-york-new-york/new-york-new-york-big-apple-coaster.tif.image.1440.720.high.jpg");
        place.setPayment(3.21d);
        dbHelper.getSession().insert(place);
        beacon.setPlaceId(place.getId());
        dbHelper.getSession().insert(beacon);

//        Beacon beacon2 = new Beacon();
//        beacon2.setMac("55:FC:90:66:59:DF");
//        beacon2.setX(0.58f);
//        beacon2.setY(0.5f);
//        beacon2.setMapId(16);
//        Place place2 = new Place();
//        place2.setName("wewewadad");
//        place2.setPayment(3.21d);
//        dbHelper.getSession().insert(place2);0

    }

}
