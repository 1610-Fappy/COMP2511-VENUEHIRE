package unsw.venues;

import java.util.ArrayList;

public class venue extends amountRooms {

    private String venueName = "";
    private ArrayList<room> rooms = new ArrayList<room>();

    public venue() {
        super();
    }

    public venue(String venueName) {
        super();
        this.venueName = venueName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void addRoom(String roomName, String roomSize) {
        rooms.add(room(roomName, roomSize));

        switch (roomSize) {

            case "large":
                incrementLargeRooms();
                break;
            
            case "medium":
                incrementMedRooms();
                break;
            
            case "small":
                incrementSmallRooms();
                break;
        }
    }
}