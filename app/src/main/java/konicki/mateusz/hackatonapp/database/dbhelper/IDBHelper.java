package konicki.mateusz.hackatonapp.database.dbhelper;

import konicki.mateusz.hackatonapp.model.DaoSession;

/**
 * Created by Mateusz on 11.02.2017.
 */
public interface IDBHelper {
    DaoSession getSession();
}
