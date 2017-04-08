package konicki.mateusz.hackatonapp.summary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.model.Beacon;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final ListView listview = (ListView) findViewById(R.id.summary_view);
        List<Beacon> beaconList = new DBHelper(this).getSession().getBeaconDao().loadAll();
        final SummaryListAdapter adapter = new SummaryListAdapter(this, beaconList);
        listview.setAdapter(adapter);

        TextView textView = (TextView) findViewById(R.id.summary_price);
        double summed_price = 0;
        for(Beacon bcn: beaconList){
            summed_price += bcn.getPlace().getPayment();
        }
        textView.setText(String.format(Locale.getDefault(), "%.2f EUR", summed_price));
    }
}
