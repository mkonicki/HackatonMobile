package konicki.mateusz.hackatonapp.summary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final ListView listview = (ListView) findViewById(R.id.summary_view);

        final SummaryListAdapter adapter = new SummaryListAdapter(this, new DBHelper(this).getSession().getBeaconDao().loadAll());
        listview.setAdapter(adapter);
    }
}
