package konicki.mateusz.hackatonapp.components.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import konicki.mateusz.hackatonapp.PlaceView;
import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.base.AlertDialogHelper;
import konicki.mateusz.hackatonapp.base.BaseView;
import konicki.mateusz.hackatonapp.base.IBaseViewModel;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.manager.BeaconAddedEvent;
import konicki.mateusz.hackatonapp.manager.BeaconRemoveEvent;
import konicki.mateusz.hackatonapp.model.Place;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class MainActivityView extends BaseView {

    private PlaceView view;

    public MainActivityView() {
        super(R.layout.activity_main);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = (PlaceView) findViewById(R.id.view);
        EventBus.getDefault().register(this);
    }

    @Override
    protected IBaseViewModel initViewModel() {
        return new MainActivityViewModel();
    }

    @Subscribe
    public void BeaconAddedEvent(BeaconAddedEvent event) {
        synchronized (this) {
            view.drawBeaconOnMap(event.getBeaconSensor().getBeacon());
        }
    }

}
