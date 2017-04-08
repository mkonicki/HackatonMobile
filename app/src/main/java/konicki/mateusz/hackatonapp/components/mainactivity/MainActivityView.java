package konicki.mateusz.hackatonapp.components.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import konicki.mateusz.hackatonapp.PlaceView;
import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.base.BaseView;
import konicki.mateusz.hackatonapp.base.IBaseViewModel;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.manager.BeaconAddedEvent;
import konicki.mateusz.hackatonapp.places.PlaceDetailActivity;
import konicki.mateusz.hackatonapp.summary.Summary;

import android.support.v4.widget.DrawerLayout;
import static konicki.mateusz.hackatonapp.R.id.toolbar;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class MainActivityView extends BaseView implements NavigationView.OnNavigationItemSelectedListener {

    private PlaceView view;
    private MainActivityAdapter activityAdapter;
    private ListView listView;

    public MainActivityView() {
        super(R.layout.activity_main);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdapter = new MainActivityAdapter(this, new DBHelper(this).getSession().getBeaconDao().loadAll());
        view = (PlaceView) findViewById(R.id.view);
        listView = (ListView) findViewById(R.id.list_beacons);
        listView.setAdapter(activityAdapter);

        EventBus.getDefault().register(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, (DrawerLayout) findViewById(R.id.drawer_layout), (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ((DrawerLayout) findViewById(R.id.drawer_layout)).setDrawerListener(toggle);
        toggle.syncState();
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, Summary.class);
            this.startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
