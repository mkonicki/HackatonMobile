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

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BluetoothConnectionlessHRSensor {

    private UUIDs uuiDs;
    private BluetoothDevice device;
    private int RSSI;

    private ADManufacturerSpecific manufacturerSpecific;

    public BluetoothConnectionlessHRSensor(BluetoothScanResult scanResult) {
        device = scanResult.getDevice();
        RSSI = scanResult.getRSSI();
        uuiDs = getUUIDsInfo(scanResult.getAdditionalData());
        manufacturerSpecific = getADManufacturerSpecific(scanResult.getAdditionalData());
    }

    public String getID() {
        return device.getAddress();
    }


    public String getName() {
        return device.getName();
    }

    public void initDeviceInfo() {
        EventBus.getDefault().register(this);
    }

    public boolean isHeartRateDevice() {

        for (UUID uuid : uuiDs.getUUIDs()) {
            Log.e("uuid",uuid.toString());
        }
        return false;
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onScanResult(BluetoothScanResult result) {
        if (result.getDevice().getAddress().equals(device.getAddress()))
            handleScanResult(result);
    }


    private void handleScanResult(BluetoothScanResult result) {
        RSSI = result.getRSSI();

        ADManufacturerSpecific adManufacturerSpecific = getADManufacturerSpecific(result.getAdditionalData());
        if (adManufacturerSpecific == null)
            return;
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

}
