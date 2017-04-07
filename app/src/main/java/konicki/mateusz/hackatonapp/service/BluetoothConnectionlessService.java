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
    private BluetoothAdapter.LeScanCallback BLEScanCallback;
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            BLEScanCallback = new BluetoothLeScanCallback(true);
        } else {
            scanCallback = new BluetoothScanCallback(true);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);
        if (checkBluetooth()) {
            scanLeDevice();
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void startScanCallback() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            bluetoothAdapter.startLeScan(BLEScanCallback);
        } else {
            if (BLEScanner == null)
                return;
            BLEScanner.startScan(filters, settings, scanCallback);
        }
    }

    protected void startScan() {
        callback.post(runnable);
        isScanning = true;
    }


    protected void stopScan() {
        callback.removeCallbacks(runnable);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            bluetoothAdapter.stopLeScan(BLEScanCallback);
        } else if (BLEScanner != null && scanCallback != null) {
            BLEScanner.stopScan(scanCallback);
        }
        isScanning = false;
    }

}