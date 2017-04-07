package konicki.mateusz.hackatonapp.manager;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.repository.BeaconRepository;
import konicki.mateusz.hackatonapp.service.BeaconSensor;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BeaconManager {

    private EventBus eventBus;
    private BeaconRepository beaconRepository;
    private List<BeaconSensor> beacons;

    public static BeaconManager instance;

    private BeaconManager(Context context) {
        eventBus = EventBus.getDefault();
        beaconRepository = new BeaconRepository(context);
        beacons = new ArrayList<>();
    }

    public static BeaconManager getInstance(Context context) {
        if (instance == null)
            instance = new BeaconManager(context);
        return instance;
    }

    @Subscribe
    public void onBeaconSensor(BeaconSensor beaconSensor) {
        if (isBeaconOnList(beaconSensor)) {
            beaconSensor.update(beaconSensor);
            return;
        }
        Beacon beacon = beaconRepository.getBeaconByMac(beaconSensor.getMac());
        beaconSensor.setBeacon(beacon);
        beacons.add(beaconSensor);
    }

    private boolean isBeaconOnList(BeaconSensor beaconSensor) {
        for (BeaconSensor beacon : beacons) {
            if (beacon.getMac() == beaconSensor.getMac())
                return true;
        }
        return false;
    }
}
