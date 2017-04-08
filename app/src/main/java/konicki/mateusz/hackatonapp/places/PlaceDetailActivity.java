package konicki.mateusz.hackatonapp.places;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import konicki.mateusz.hackatonapp.R;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.model.Place;

/**
 * An activity representing a single Place detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 */
public class PlaceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);

        setSupportActionBar(toolbar);

        long id = this.getIntent().getLongExtra("ID", 0);
        Place place = new DBHelper(this).getSession().getPlaceDao().load(id);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Glide.with(imageView.getContext())
                .load(place.getUrl())
                .into(imageView);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putString(PlaceDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PlaceDetailFragment.ARG_ITEM_ID));
            PlaceDetailFragment fragment = new PlaceDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.place_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
