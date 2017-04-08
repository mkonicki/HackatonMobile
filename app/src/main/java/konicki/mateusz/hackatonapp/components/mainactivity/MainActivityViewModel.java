package konicki.mateusz.hackatonapp.components.mainactivity;


import android.support.v7.app.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import konicki.mateusz.hackatonapp.base.AlertDialogHelper;
import konicki.mateusz.hackatonapp.base.BaseViewModel;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.database.dbhelper.IDBHelper;
import konicki.mateusz.hackatonapp.events.PaymentEvent;
import konicki.mateusz.hackatonapp.manager.BeaconAddedEvent;
import konicki.mateusz.hackatonapp.model.Place;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class MainActivityViewModel extends BaseViewModel {

    private EventBus eventBus;
    private IDBHelper dbhelper;

    public MainActivityViewModel() {
        eventBus = EventBus.getDefault();
    }

    @Override
    public void register() {
        super.register();
        this.dbhelper = new DBHelper(context);
        eventBus.register(this);
    }


    @Subscribe
    public void BeaconPaidEvent(PaymentEvent event) {
        AlertDialogHelper alertDialogHelper = new AlertDialogHelper();
        AlertDialog dialog = alertDialogHelper.createAlertDialog(context,
                "You paid for entrace to: " + event.getBeacon().getPlace().getName()
                , "Cost " + event.getBeacon().getPlace().getPayment()+" Euro");
        dialog.show();
    }


}
