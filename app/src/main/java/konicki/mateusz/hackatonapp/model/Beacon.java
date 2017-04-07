package konicki.mateusz.hackatonapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

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


}
