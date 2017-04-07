package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


/**
 * Created by Mateusz on 07.04.2017.
 */
@Entity
public class HistoracialPayment {

    @Id(autoincrement = true)
    private long id;

    private Date date;

    private long placeId;
    @ToMany
    @JoinEntity(
            entity = HistoricalPaymentPlace.class,
            sourceProperty = "placeId",
            targetProperty = "historicalPaymentId"
    )
    private List<HistoracialPayment> payments;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2106270845)
    private transient HistoracialPaymentDao myDao;
    @Generated(hash = 368912539)
    public HistoracialPayment(long id, Date date, long placeId) {
        this.id = id;
        this.date = date;
        this.placeId = placeId;
    }
    @Generated(hash = 1854217814)
    public HistoracialPayment() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getPlaceId() {
        return this.placeId;
    }
    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1238684041)
    public List<HistoracialPayment> getPayments() {
        if (payments == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HistoracialPaymentDao targetDao = daoSession.getHistoracialPaymentDao();
            List<HistoracialPayment> paymentsNew = targetDao
                    ._queryHistoracialPayment_Payments(id);
            synchronized (this) {
                if (payments == null) {
                    payments = paymentsNew;
                }
            }
        }
        return payments;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1083720232)
    public synchronized void resetPayments() {
        payments = null;
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
    @Generated(hash = 1847067553)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHistoracialPaymentDao() : null;
    }
}
