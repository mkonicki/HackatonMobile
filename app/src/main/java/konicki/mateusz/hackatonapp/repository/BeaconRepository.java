package konicki.mateusz.hackatonapp.repository;

import android.content.Context;

import java.util.List;

import konicki.mateusz.hackatonapp.model.Beacon;
import konicki.mateusz.hackatonapp.model.BeaconDao;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class BeaconRepository extends BaseRepository<Beacon, BeaconDao> {
    public BeaconRepository(Context context) {
        super(context);
    }

    @Override
    protected BeaconDao getDao() {
        return idbHelper.getSession().getBeaconDao();
    }

    public Beacon getBeaconByMac(String mac) {
        List<Beacon> beacons = getDao().queryBuilder().where(BeaconDao.Properties.Mac.eq(mac)).list();
        if (beacons == null || beacons.isEmpty())
            return null;
        return beacons.get(0);
    }
}
