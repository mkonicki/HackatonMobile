package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Mateusz on 07.04.2017.
 */
@Entity
public class HistoricalPaymentPlace {
    @Id(autoincrement = true)
    private long id;

    private long placeId;

    private long historicalPaymentId;

}
