package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 07.04.2017.
 */
@Entity
public class Beacon {
    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String mac;


    private Long placeId;


    @ToOne(joinProperty = "placeId")
    private Place place;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1190523493)
    private transient BeaconDao myDao;


    @Generated(hash = 798105789)
    public Beacon(Long id, String mac, Long placeId) {
        this.id = id;
        this.mac = mac;
        this.placeId = placeId;
    }


    @Generated(hash = 1143354403)
    public Beacon() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getMac() {
        return this.mac;
    }


    public void setMac(String mac) {
        this.mac = mac;
    }


    public Long getPlaceId() {
        return this.placeId;
    }


    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }


    @Generated(hash = 2133679615)
    private transient Long place__resolvedKey;


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 887950062)
    public Place getPlace() {
        Long __key = this.placeId;
        if (place__resolvedKey == null || !place__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlaceDao targetDao = daoSession.getPlaceDao();
            Place placeNew = targetDao.load(__key);
            synchronized (this) {
                place = placeNew;
                place__resolvedKey = __key;
            }
        }
        return place;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 974283990)
    public void setPlace(Place place) {
        synchronized (this) {
            this.place = place;
            placeId = place == null ? null : place.getId();
            place__resolvedKey = placeId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1978483639)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBeaconDao() : null;
    }


}
