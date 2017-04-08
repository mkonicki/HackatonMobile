package konicki.mateusz.hackatonapp.manager;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import konicki.mateusz.hackatonapp.events.PaymentEvent;
import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.model.BeaconType;
import konicki.mateusz.hackatonapp.repository.BeaconRepository;
import konicki.mateusz.hackatonapp.service.BeaconSensor;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BeaconManager {


    private BeaconRepository beaconRepository;
    private List<BeaconSensor> beacons;
    private BeaconSensor beaconSensor;
    public static BeaconManager instance;

    private BeaconManager(Context context) {
        beaconRepository = new BeaconRepository(context);
        beacons = new ArrayList<>();
    }

    public static BeaconManager getInstance(Context context) {
        if (instance == null) {
            instance = new BeaconManager(context);
            EventBus.getDefault().register(instance);
        }
        return instance;
    }

    @Subscribe
    public void onBeaconSensor(BeaconSensor beaconSensor) {
        Beacon beacon = beaconRepository.getBeaconByMac(beaconSensor.getMac());
        if (beacon == null)
            return;
        beaconSensor.setBeacon(beacon);
        if (isBeaconOnList(beaconSensor) != null) {
            BeaconSensor sensor = isBeaconOnList(beaconSensor);
            sensor.update(beaconSensor);
            handleBeaconBehavior(sensor);
            return;
        }
        if (beacon.getType() == BeaconType.ENTRANCE) {
            EventBus.getDefault().post(new EntranceEvent());
        }
        beacons.add(beaconSensor);
        handleBeaconBehavior(beaconSensor);
    }

    private void handleBeaconBehavior(BeaconSensor beaconSensor) {
        if (beaconSensor.isPayable()) {
            beaconSensor.pay();
            updateBeaconData(beaconSensor);
            EventBus.getDefault().post(new PaymentEvent(beaconSensor.getBeacon()));
            return;
        }
        if (this.beaconSensor != null && this.beaconSensor.getMac().equals(beaconSensor.getMac())) {
            this.beaconSensor = beaconSensor;
        }
        if (this.beaconSensor != null && this.beaconSensor.queality() > beaconSensor.queality()) {
            return;
        }
        beaconSensor.saw();
        updateBeaconData(beaconSensor);
        this.beaconSensor = beaconSensor;
        EventBus.getDefault().post(new BeaconAddedEvent(beaconSensor));
    }

    private BeaconSensor isBeaconOnList(BeaconSensor beaconSensor) {
        for (BeaconSensor beacon : beacons) {
            if (beacon.getMac().equals(beaconSensor.getMac()))
                return beacon;
        }
        return null;
    }

    private void updateBeaconData(BeaconSensor sensor) {
        for (BeaconSensor beacon : beacons) {
            if (beacon.getMac().equals(sensor.getMac())) {
                int index = beacons.indexOf(beacon);
                beacons.set(index, sensor);
            }
        }
    }
}
