package konicki.mateusz.hackatonapp.repository;

import android.content.Context;

import konicki.mateusz.hackatonapp.model.Place;
import konicki.mateusz.hackatonapp.model.PlaceDao;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class PlaceRepository extends BaseRepository<Place, PlaceDao> {
    public PlaceRepository(Context context) {
        super(context);
    }

    @Override
    protected PlaceDao getDao() {
        return idbHelper.getSession().getPlaceDao();
    }

    public Place getPlaceById(long id){
        return getDao().load(id);
    }
}
