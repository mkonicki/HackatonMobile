package konicki.mateusz.hackatonapp.base;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by Mateusz on 07.04.2017.
 */

public interface IBaseViewModel<T extends IBaseModel> {
    void register();
    void startup();
    void unregister();
    ObservableField<T> getModel();
    void setContext(Context context);

}