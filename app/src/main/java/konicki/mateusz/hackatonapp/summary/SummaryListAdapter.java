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
import konicki.mateusz.hackatonapp.places.PlaceDetailActivity;

/**
 * Created by Esroh on 08-Apr-17.
 */

public class SummaryListAdapter extends BaseAdapter {

    private List<Beacon> beaconList;
    private Context context;

    public SummaryListAdapter(Context context, List<Beacon> beaconList) {
        this.beaconList = beaconList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beaconList.size();
    }

    @Override
    public Beacon getItem(int position) {
        return beaconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return beaconList.get(position).getMapId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.summary_list_item, null);

        TextView textView = (TextView) view.findViewById(R.id.item_text);
        textView.setText(String.format(Locale.getDefault(), "%s %.2f EUR", getItem(position).getPlace().getName(), getItem(position).getPlace().getPayment()));
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaceDetailActivity.class);
            intent.putExtra("ID", getItem(position).getPlaceId());
            context.startActivity(intent);
        });
        return view;
    }
}
