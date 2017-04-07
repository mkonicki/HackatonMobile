package konicki.mateusz.hackatonapp.manager;

import konicki.mateusz.hackatonapp.service.BeaconSensor;

/**
 * Created by Mateusz on 07.04.2017.
 */
public class BeaconRemoveEvent {
    private final BeaconSensor beaconSensor;

    public BeaconRemoveEvent(BeaconSensor beaconSensor) {
        this.beaconSensor = beaconSensor;
    }

    public BeaconSensor getBeaconSensor() {
        return beaconSensor;
    }
}
