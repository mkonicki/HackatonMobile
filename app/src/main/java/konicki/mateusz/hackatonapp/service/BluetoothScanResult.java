package konicki.mateusz.hackatonapp.service;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BluetoothScanResult {
    private byte[] AdditionalData;
    private int RSSI;
    private BluetoothDevice Device;

    public BluetoothScanResult(byte[] additionalData, int RSSI, BluetoothDevice device) {
        this.AdditionalData = additionalData;
        this.RSSI = RSSI;
        this.Device = device;
    }

    public byte[] getAdditionalData() {
        return AdditionalData;
    }

    public int getRSSI() {
        return RSSI;
    }

    public BluetoothDevice getDevice() {
        return Device;
    }
}
