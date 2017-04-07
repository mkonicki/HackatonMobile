package konicki.mateusz.hackatonapp.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mateusz on 07.04.2017.
 */
//public abstract class BaseView extends AppCompatActivity {
//    protected ViewDataBinding dataBinding;
//    protected IBaseViewModel viewModel;
//    protected int layoutId;
//    protected View currentView;
//
//    protected EventBus eventBus;
//    protected BaseView(int layoutId) {
//        this.layoutId = layoutId;
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initializeInjection();
//        viewModel = initViewModel();
//        currentView = initView();
//        setContentView(currentView);
//        viewModel.startup();
//        viewModel.register();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        eventBus.post(new ActivityResumeEvent(this));
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        viewModel.unregister();
//        ViewHelper.unbindDrawables(currentView);
//        eventBus.post(new ActivityDestroyedEvent(this));
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        eventBus.post(new ActivityResultEvent(resultCode, requestCode, data));
//    }
//    protected View initView() {
//        dataBinding = bindData();
//        View view = dataBinding.getRoot();
//        ButterKnife.setDebug(true);
//        ButterKnife.bind(this, view);
//        return view;
//    }
//    protected ViewDataBinding bindData() {
//        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, null, false);
//        binding.setVariable(BR.viewModel, viewModel);
//        return binding;
//    }
//    protected abstract IBaseViewModel initViewModel();
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        eventBus.post(new PermissionGrantedEvent(permissions, grantResults));
//    }
//    protected void initializeInjection() {
//        DaggerCoreComponentProvider.getInstance().provideComponent().inject(this);
//    }
//}