package konicki.mateusz.hackatonapp.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BluetoothLeScanCallback implements BluetoothAdapter.LeScanCallback {

    private boolean supportConnectionlessProtocol;

    public BluetoothLeScanCallback(boolean supportConnectionlessProtocol) {
        this.supportConnectionlessProtocol = supportConnectionlessProtocol;
    }


    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        BluetoothScanResult scanResult = new BluetoothScanResult(scanRecord, rssi, device);
        if (supportConnectionlessProtocol) {
            processConnectionlessSensor(scanResult);
            return;
        }
    }

    private void processConnectionlessSensor(BluetoothScanResult result) {
        if (result.getAdditionalData() == null)
            return;
        EventBus.getDefault().post(result);
        EventBus.getDefault().post(new BluetoothConnectionlessHRSensor(result));
    }
}
