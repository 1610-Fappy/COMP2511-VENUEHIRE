package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class booking {
    
    private String id = "";
    private LocalDate startDate;
    private LocalDate enddDate;
    private ArrayList<room> rooms = new ArrayList<room>();

    public booking() {
        super();
    }

    public booking (String id, LocalDate startDate, LocalDate endDate) {
        setBooking(id, startDate, endDate);
    }

    public void setBooking(String id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.enddDate = endDate;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return enddDate;
    }
    
    public void addRoomBook(room newRoom) {
        rooms.add(newRoom);
    }

    public ArrayList<room> getbookedRooms() {
        return rooms;
    }
}