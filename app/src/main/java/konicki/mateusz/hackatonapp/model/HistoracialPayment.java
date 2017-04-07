package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;


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
}
