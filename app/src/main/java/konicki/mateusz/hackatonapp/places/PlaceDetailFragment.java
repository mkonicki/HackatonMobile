package konicki.mateusz.hackatonapp.places;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Stream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.model.Place;
import konicki.mateusz.hackatonapp.model.Queue;

public class PlaceDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    public PlaceDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_detail, container, false);

        Activity activity = this.getActivity();
        long id = activity.getIntent().getLongExtra("ID", 0);
        Place place = new DBHelper(activity).getSession().getPlaceDao().load(id);
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText(place.getName());
        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(place.getDescription());
        TextView price = (TextView) rootView.findViewById(R.id.price);
        final String[] a = {"Predicted queue at 8.04:"};

        List<Queue> queues = place.getQueues();
        Stream.of(queues).forEach(i->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

            a[0]+="\n " + simpleDateFormat.format(i.getPredictedTime())+ " - " + i.getQueueSize();});
        TextView que = (TextView) rootView.findViewById(R.id.queue);
        que.setText(a[0]);
        price.setText(String.valueOf(place.getPayment()));
        return rootView;
    }
}
