package konicki.mateusz.hackatonapp.paymentservice;

import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;

import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.database.dbhelper.IDBHelper;
import konicki.mateusz.hackatonapp.events.PaymentEvent;
import konicki.mateusz.hackatonapp.model.HistoracialPayment;
import konicki.mateusz.hackatonapp.service.ViewServiceBase;
import konicki.mateusz.hackatonapp.service.ViewServiceBinder;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class PaymentService extends ViewServiceBase {
    private IDBHelper idbHelper;


    @Override
    public IBinder onBind(Intent intent) {
        return new ViewServiceBinder<>(this);
    }

    @Override
    public boolean onResume() {
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initialize();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void initialize() {
        EventBus.getDefault().register(this);
        idbHelper = new DBHelper(this);
    }

    @Subscribe
    public void onPaymentEvent(PaymentEvent event) {
        Date now = new Date(System.currentTimeMillis());
        HistoracialPayment payment = new HistoracialPayment();
        payment.setPlaceId(event.getBeacon().getPlaceId());
        payment.setDate(now);
        idbHelper.getSession().insert(payment);
    }
}
