package konicki.mateusz.hackatonapp.events;

import konicki.mateusz.hackatonapp.model.Beacon;

/**
 * Created by Mateusz on 07.04.2017.
 */
public class PaymentEvent {
    private final Beacon beacon;

    public PaymentEvent(Beacon beacon) {
        this.beacon = beacon;
    }

    public Beacon getBeacon() {
        return beacon;
    }


}
