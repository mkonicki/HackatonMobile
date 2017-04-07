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
    private final double PAYABLE = 98;
    private final double VISIBLE = 50;

    private ADManufacturerSpecific manufacturerSpecific;
    private Beacon beacon;
    private boolean wasPaid = false;
    private boolean wasVisible = false;

    BeaconSensor(BluetoothScanResult scanResult) {
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

    private int getRSSI() {
        return RSSI;
    }

    public boolean isPayable() {
        return !wasPaid && queality() >= PAYABLE;
    }


    public void update(BeaconSensor beaconSensor) {

        RSSI = beaconSensor.getRSSI();
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public double queality() {
        double dBm = RSSI;
        if (dBm < -100) {
            dBm = -100;
        } else if (dBm > -50) {
            dBm = -50;
        }
        return 2.0 * (dBm + 100.0);
    }


    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public void pay() {
        this.wasPaid = true;
    }

    public boolean isVisible() {
        return !wasVisible && queality() > VISIBLE;
    }


    public boolean wasPaidForEntrance() {
        return wasPaid;
    }

    public void saw() {
        wasVisible = true;
        if (queality() < 10)
            wasPaid = false;
    }
}
