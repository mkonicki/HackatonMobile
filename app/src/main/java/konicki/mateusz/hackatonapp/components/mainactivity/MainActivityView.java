package konicki.mateusz.hackatonapp.components.mainactivity;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.base.BaseView;
import konicki.mateusz.hackatonapp.base.IBaseViewModel;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class MainActivityView extends BaseView {
    public MainActivityView() {
        super(R.layout.activity_main);
    }

    @Override
    protected IBaseViewModel initViewModel() {
        return new MainActivityViewModel();
    }
}
