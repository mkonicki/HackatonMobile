package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

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
    @ToMany
    @JoinEntity(
            entity = HistoricalPaymentPlace.class,
            sourceProperty = "placeId",
            targetProperty = "historicalPaymentId"
    )
    private List<HistoracialPayment> payments;
}
