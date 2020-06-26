package unsw.venues;

public class room {

    private String roomName = "";
    private String roomSize = "";
    private ArrayList<booking> bookings = new ArrayList<booking>();

    public room() {
        super();
    }

    public room(String roomName, String roomSize) {
        this.roomName = roomName;
        this.roomSize = roomSize;
    }

    public String getRoomName() {
        return roomName;
    }
}