package konicki.mateusz.hackatonapp.summary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.model.HistoracialPayment;
import konicki.mateusz.hackatonapp.model.Place;
import konicki.mateusz.hackatonapp.places.PlaceDetailActivity;

/**
 * Created by Esroh on 08-Apr-17.
 */

public class SummaryListAdapter extends BaseAdapter {

    private List<Place> beaconList;
    private Context context;

    public SummaryListAdapter(Context context, List<Place> beaconList) {
        this.beaconList = beaconList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beaconList.size();
    }

    @Override
    public Place getItem(int position) {
        return beaconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return beaconList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.summary_list_item, null);

        TextView textView = (TextView) view.findViewById(R.id.place_name);
        textView.setText(String.format(Locale.getDefault(), "%s ", getItem(position).getName()));
        TextView textView2 = (TextView) view.findViewById(R.id.place_price);
        textView2.setText(String.format(Locale.getDefault(), "%.2f EUR",getItem(position).getPayment()));



        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaceDetailActivity.class);
            intent.putExtra("ID", getItem(position).getId());
            context.startActivity(intent);
        });
        return view;
    }
}
