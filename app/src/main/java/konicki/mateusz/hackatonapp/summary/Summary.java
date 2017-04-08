package konicki.mateusz.hackatonapp.summary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import konicki.mateusz.hackatonapp.EntranceView.EntranceView;
import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.base.AlertDialogHelper;
import konicki.mateusz.hackatonapp.components.mainactivity.MainActivityView;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.model.Entrance;
import konicki.mateusz.hackatonapp.model.HistoracialPayment;
import konicki.mateusz.hackatonapp.model.HistoracialPaymentDao;
import konicki.mateusz.hackatonapp.model.Place;

public class Summary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Long date = getIntent().getLongExtra("DATE", 0);

        final ListView listview = (ListView) findViewById(R.id.summary_view);
        List<HistoracialPayment> historacialPayments = new DBHelper(this).getSession().getHistoracialPaymentDao().queryBuilder().where(HistoracialPaymentDao.Properties.Date.ge(date)).list();


        List<Place> beaconList = Stream.of(historacialPayments).map(payment -> payment.getPlace()).collect(Collectors.toList());
        final SummaryListAdapter adapter = new SummaryListAdapter(this, beaconList);
        listview.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView beginDate = (TextView) findViewById(R.id.begin_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        beginDate.setText("Begin date: " + dateFormat.format(historacialPayments.get(0).getDate()));


        TextView endDate = (TextView) findViewById(R.id.end_date);
        endDate.setText("End date: " + dateFormat.format(historacialPayments.get(historacialPayments.size()-1).getDate()));
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.summary_price);
        double summed_price = 0;
        for (Place bcn : beaconList) {
            summed_price += bcn.getPayment();
        }
        textView.setText(String.format(Locale.getDefault(), "%.2f EUR", summed_price));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, (DrawerLayout) findViewById(R.id.drawer_layout), (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ((DrawerLayout) findViewById(R.id.drawer_layout)).setDrawerListener(toggle);
        toggle.syncState();
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

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, MainActivityView.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, EntranceView.class);
            this.startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
