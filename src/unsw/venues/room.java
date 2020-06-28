package unsw.venues;

import java.util.ArrayList;
import java.time.LocalDate;

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

    public ArrayList<booking> getBookings() {
        return bookings;
    }

    public Boolean CheckBooking(LocalDate start, LocalDate end) {
        if (bookings.get(0).getStartDate().isAfter(end)) {
            return true;
        }
        for (int i = 1; i < bookings.size(); i++) {
            if (bookings.get(i - 1).getEndDate().isBefore(start) && bookings.get(i).getStartDate().isAfter(end)) {
                return true;
            }
        }
        return false;
    }

    public void addBooking(booking newBooking) {
        bookings.add(newBooking);
    }

    public booking cancelBooking(String id) {
        booking tempHold = new booking();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(id)) {
                tempHold = bookings.get(i);
                bookings.remove(i);
            }
        }
        return tempHold;
    }
}