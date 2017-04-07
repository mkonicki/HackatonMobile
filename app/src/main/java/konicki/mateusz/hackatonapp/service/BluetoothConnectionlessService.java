package konicki.mateusz.hackatonapp.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

/**
 * Created by Mateusz on 07.04.2017.
 */
public class BluetoothConnectionlessService extends BluetoothService {

    private final int TIMESTAMP = 1200;
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
        scanCallback = new BluetoothScanCallback(this);
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