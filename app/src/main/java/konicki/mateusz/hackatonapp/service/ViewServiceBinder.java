package konicki.mateusz.hackatonapp.service;

import android.app.Service;
import android.os.Binder;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class ViewServiceBinder<TService extends Service> extends Binder {
    private final TService service;
    public ViewServiceBinder(TService service) {
        this.service = service;
    }
    public TService getService() {
        return service;
    }
}