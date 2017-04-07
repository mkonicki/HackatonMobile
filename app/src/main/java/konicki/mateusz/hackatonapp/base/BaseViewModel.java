package konicki.mateusz.hackatonapp.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

/**
 * Created by Mateusz on 07.04.2017.
 */
public abstract class BaseViewModel<T extends IBaseModel> extends BaseObservable implements IBaseViewModel<T> {

    protected Context context;
    protected ObservableField<T> model;
    protected BaseViewModel(T model) {
        this.model = new ObservableField<>(model);
    }
    protected BaseViewModel() {
        this.model = new ObservableField<>();
    }
    @Bindable
    public ObservableField<T> getModel(){
        return model;
    }
    @Override
    public void register() {
//
    }
    @Override
    public void startup() {
    }
    @Override
    public void unregister() {
//
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}