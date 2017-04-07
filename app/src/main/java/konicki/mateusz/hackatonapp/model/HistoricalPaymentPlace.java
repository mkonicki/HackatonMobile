package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mateusz on 07.04.2017.
 */
@Entity
public class HistoricalPaymentPlace {
    @Id(autoincrement = true)
    private long id;

    private long placeId;

    private long historicalPaymentId;

    @Generated(hash = 48485184)
    public HistoricalPaymentPlace(long id, long placeId, long historicalPaymentId) {
        this.id = id;
        this.placeId = placeId;
        this.historicalPaymentId = historicalPaymentId;
    }

    @Generated(hash = 329391284)
    public HistoricalPaymentPlace() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public long getHistoricalPaymentId() {
        return this.historicalPaymentId;
    }

    public void setHistoricalPaymentId(long historicalPaymentId) {
        this.historicalPaymentId = historicalPaymentId;
    }

}
