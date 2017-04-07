package konicki.mateusz.hackatonapp.service;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.neovisionaries.bluetooth.ble.advertising.ADManufacturerSpecific;
import com.neovisionaries.bluetooth.ble.advertising.ADPayloadParser;
import com.neovisionaries.bluetooth.ble.advertising.ADStructure;
import com.neovisionaries.bluetooth.ble.advertising.UUIDs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import konicki.mateusz.hackatonapp.model.Beacon;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BeaconSensor {

    private UUIDs uuiDs;
    private BluetoothDevice device;
    private int RSSI;

    private ADManufacturerSpecific manufacturerSpecific;
    private Beacon beacon;

    public BeaconSensor(BluetoothScanResult scanResult) {
        device = scanResult.getDevice();
        RSSI = scanResult.getRSSI();
        uuiDs = getUUIDsInfo(scanResult.getAdditionalData());
        manufacturerSpecific = getADManufacturerSpecific(scanResult.getAdditionalData());
    }

    public String getMac() {
        return device.getAddress();
    }


    public String getName() {
        return device.getName();
    }

    private ADManufacturerSpecific getADManufacturerSpecific(byte[] bytes) {
        if (bytes == null)
            return null;
        List<ADStructure> structures =
                ADPayloadParser.getInstance().parse(bytes);
        for (ADStructure structure : structures) {
            if (structure instanceof ADManufacturerSpecific) {
                return (ADManufacturerSpecific) structure;
            }
        }
        return null;
    }

    private UUIDs getUUIDsInfo(byte[] bytes) {
        if (bytes == null)
            return null;
        List<ADStructure> structures =
                ADPayloadParser.getInstance().parse(bytes);
        for (ADStructure structure : structures) {
            if (structure instanceof UUIDs) {
                return (UUIDs) structure;
            }
        }
        return null;
    }

    public int getRSSI() {
        return RSSI;
    }

    public void update(BeaconSensor beaconSensor) {
        RSSI = beaconSensor.getRSSI();
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }
}
