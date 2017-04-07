package konicki.mateusz.hackatonapp.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;

import java.util.UUID;

/**
 * Created by Mateusz on 07.04.2017.
 */
public class BluetoothConnectionlessService extends BluetoothService {

    private final int TIMESTAMP = 3000;
    private ScanCallback scanCallback;
    private Runnable runnable;
    private Handler callback;

    public BluetoothConnectionlessService() {
        super();
        callback = new Handler();
        runnable = () -> {
            stopScan();
            startScanCallback();
            callback.postDelayed(runnable, TIMESTAMP);
        };
        scanCallback = new BluetoothScanCallback();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);
        if (checkBluetooth()) {
            scanLeDevice();
        }
        return result;
    }

    private void startScanCallback() {
        if (BLEScanner == null)
            return;
        ScanFilter filter = new ScanFilter.Builder().setServiceUuid(new ParcelUuid(UUID.fromString("0000180f-0000-1000-8000-00805f9b34fb"))).build();
        filters.add(filter);
        BLEScanner.startScan(filters, settings, scanCallback);
    }

    protected void startScan() {
        callback.post(runnable);
        isScanning = true;
    }


    protected void stopScan() {
        callback.removeCallbacks(runnable);
        if (BLEScanner != null && scanCallback != null) {
            BLEScanner.stopScan(scanCallback);
        }
        isScanning = false;
    }

}