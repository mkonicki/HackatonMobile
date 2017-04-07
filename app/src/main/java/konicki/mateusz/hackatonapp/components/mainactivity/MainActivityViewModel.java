package konicki.mateusz.hackatonapp.components.mainactivity;


import org.greenrobot.eventbus.EventBus;

import konicki.mateusz.hackatonapp.base.BaseViewModel;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class MainActivityViewModel extends BaseViewModel {

    private EventBus eventBus;

    public MainActivityViewModel() {
        eventBus = EventBus.getDefault();
    }

    @Override
    public void register() {
        super.register();
        eventBus.register(this);
    }


}
