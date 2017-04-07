package konicki.mateusz.hackatonapp.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import konicki.mateusz.hackatonapp.database.dbhelper.DBHelper;
import konicki.mateusz.hackatonapp.database.dbhelper.IDBHelper;
import konicki.mateusz.hackatonapp.model.Place;


/**
 * Created by Mateusz on 13.02.2017.
 */
//
public abstract class BaseRepository<Entity, DAO extends AbstractDao<Entity, Long>>{

    protected IDBHelper idbHelper;

    private BaseRepository(IDBHelper idbHelper) {
        this.idbHelper = idbHelper;
    }

    public BaseRepository(Context context) {
        this(new DBHelper(context));
    }

    protected abstract DAO getDao();


}
