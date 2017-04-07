package konicki.mateusz.hackatonapp.service;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mateusz on 07.04.2017.
 */


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BluetoothScanCallback extends ScanCallback {

    private boolean supportConnectionlessProtocol;

    public BluetoothScanCallback(boolean supportConnectionlessProtocol) {
        this.supportConnectionlessProtocol = supportConnectionlessProtocol;
    }

    @Override
    public void onScanResult(int callbackType, ScanResult result) {
        BluetoothScanResult scanResult = new BluetoothScanResult(result.getScanRecord().getBytes(), result.getRssi(), result.getDevice());
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
