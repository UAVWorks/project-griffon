package drone;

import com.google.gson.Gson;
import geocoordinate.BaseCoordinate;
import geocoordinate.GeoCoordinate;

public class LinkedDrone extends BaseDrone {

    private ConnectiveLink droneLink;

    public LinkedDrone(int id, Gson gson, ConnectiveLink droneLink) {
        super(id,gson);
        this.pitch = 0;
        this.roll = 0;
        this.yaw = 0;

        this.droneLink = droneLink;
        droneLink.setOnInputReceived(s -> {
            AlterableDrone mockDrone = gson.fromJson(s, AlterableDrone.class);
            mockDrone.setCoordinate(new BaseCoordinate(gson.fromJson(s, BaseCoordinate.class)));
            copyStatsFromDrone(mockDrone);
        });
        droneLink.startLink();
    }
}
