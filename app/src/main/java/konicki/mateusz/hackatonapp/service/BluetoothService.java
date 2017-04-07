package konicki.mateusz.hackatonapp.service;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 07.04.2017.
 */

public abstract class BluetoothService  extends ViewServiceBase {
    protected static BluetoothManager bluetoothManager;
    protected static BluetoothAdapter bluetoothAdapter;
    protected static BluetoothLeScanner BLEScanner;
    protected static ScanSettings settings;
    protected static List<ScanFilter> filters;
    protected boolean isScanning = false;
    protected static boolean isInitialized = false;
    protected static Object lock = new Object();

    @Override
    public void initialize() {
        if (BLEScanner != null)
            return;
        initializeBluetoothForSDK21();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new ViewServiceBinder<>(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        synchronized (lock) {
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) || isInitialized) {
                return super.onStartCommand(intent, flags, startId);
            }

            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = bluetoothManager.getAdapter();

            if (checkBluetooth()) {
                initialize();
            }
            scanLeDevice();
            int result = super.onStartCommand(intent, flags, startId);

            isInitialized = true;

            return result;
        }
    }


    @TargetApi(21)
    private void initializeBluetoothForSDK21() {
        if (Build.VERSION.SDK_INT < 21)
            return;

        BLEScanner = bluetoothAdapter.getBluetoothLeScanner();
        settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();
        filters = new ArrayList<>();
    }


    protected Boolean checkBluetooth() {
        if (bluetoothAdapter.isEnabled())
            return true;
        this.isScanning = false;
        BLEScanner = null;

        return false;
    }

    public void scanLeDevice() {
        if (isScanning)
            return;
        initialize();
        startScan();
    }

    public boolean onResume() {
        return !(bluetoothAdapter == null || !bluetoothAdapter.isEnabled());
    }

    protected abstract void startScan();

    protected abstract void stopScan();


}