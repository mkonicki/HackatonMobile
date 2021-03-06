package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 07.04.2017.
 */

@Entity
public class Place {
    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String url;

    private String description;

    private Double payment;

    @ToMany(referencedJoinProperty = "placeId")
    private List<Beacon> beacons;

    @ToMany(referencedJoinProperty = "placeId")
    private List<Queue> queues;


    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 780466496)
    private transient PlaceDao myDao;

    @Generated(hash = 1925038192)
    public Place(Long id, String name, String url, String description,
                 Double payment) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.payment = payment;
    }

    @Generated(hash = 1170019414)
    public Place() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPayment() {
        return this.payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 323467830)
    public List<Beacon> getBeacons() {
        if (beacons == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BeaconDao targetDao = daoSession.getBeaconDao();
            List<Beacon> beaconsNew = targetDao._queryPlace_Beacons(id);
            synchronized (this) {
                if (beacons == null) {
                    beacons = beaconsNew;
                }
            }
        }
        return beacons;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 2051105646)
    public synchronized void resetBeacons() {
        beacons = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */

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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
 

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 8134869)
    public synchronized void resetQueues() {
        queues = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 597092803)
    public List<Queue> getQueues() {
        if (queues == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QueueDao targetDao = daoSession.getQueueDao();
            List<Queue> queuesNew = targetDao._queryPlace_Queues(id);
            synchronized (this) {
                if (queues == null) {
                    queues = queuesNew;
                }
            }
        }
        return queues;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2040295234)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlaceDao() : null;
    }
}
