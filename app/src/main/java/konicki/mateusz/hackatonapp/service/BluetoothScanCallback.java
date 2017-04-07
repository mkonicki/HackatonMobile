package konicki.mateusz.hackatonapp.service;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mateusz on 07.04.2017.
 */


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothScanCallback extends ScanCallback {

    private Context context;


    public BluetoothScanCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onScanResult(int callbackType, ScanResult result) {
        BluetoothScanResult scanResult = new BluetoothScanResult(result.getScanRecord().getBytes(), result.getRssi(), result.getDevice());

        processConnectionlessSensor(scanResult);

    }

    private void processConnectionlessSensor(BluetoothScanResult result) {
        if (result.getAdditionalData() == null)
            return;
        EventBus.getDefault().post(new BeaconSensor(result));

    }


}
