package konicki.mateusz.hackatonapp.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import konicki.mateusz.hackatonapp.BR;

/**
 * Created by Mateusz on 07.04.2017.
 */
public abstract class BaseView extends AppCompatActivity {
    protected ViewDataBinding dataBinding;
    protected IBaseViewModel viewModel;
    protected int layoutId;
    protected View currentView;

    protected BaseView(int layoutId) {
        this.layoutId = layoutId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = initViewModel();
        viewModel.setContext(this);
        currentView = initView();
        setContentView(currentView);
        viewModel.startup();
        viewModel.register();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.unregister();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected View initView() {
        dataBinding = bindData();
        View view = dataBinding.getRoot();
        return view;
    }
    protected ViewDataBinding bindData() {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, null, false);
        binding.setVariable(BR.viewModel, viewModel);
        return binding;
    }
    protected abstract IBaseViewModel initViewModel();

}