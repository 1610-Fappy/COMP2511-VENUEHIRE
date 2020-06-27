package unsw.venues;

import java.util.ArrayList;
import java.time.LocalDate;

public class venue extends amountRooms {

    private String venueName = "";
    private ArrayList<room> largeRooms = new ArrayList<room>();
    private ArrayList<room> medRooms = new ArrayList<room>();
    private ArrayList<room> smallRooms = new ArrayList<room>();


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

        switch (roomSize) {

            case "large":
                largeRooms.add(new room(roomName, roomSize));
                incrementLargeRooms();
                break;
            
            case "medium":
                medRooms.add(new room(roomName, roomSize));
                incrementMedRooms();
                break;
            
            case "small":
                smallRooms.add(new room(roomName, roomSize));
                incrementSmallRooms();
                break;
        }
    }

    

    public Boolean checkAmountRooms(int large, int medium, int small) {
        if (getlargeRooms() >= large && getmedRooms() >= medium && getsmallRooms() >= small) {
            System.out.println(getVenueName());
            return true;
        }

        return false;
    }

    public ArrayList<room> checkRoomBooking(ArrayList<room> roomList, String id, int num, LocalDate start, LocalDate end) {
        
        ArrayList<room> availRooms = new ArrayList<room>();

        for (int i = 0; i < roomList.size() && num > 0; i++) {
            if (roomList.get(i).getBookings().size() != 0) {
                if (roomList.get(i).CheckBooking(start, end)) {
                    availRooms.add(roomList.get(i));
                    roomList.get(i).addBooking(new booking(id, start, end));
                    num--;
                }
            }
            else {
                availRooms.add(roomList.get(i));
                roomList.get(i).addBooking(new booking(id, start, end));
                num--;                
            }
        }

        if (num == 0) {
            return availRooms;
        }
        return new ArrayList<room>();
    }

    public ArrayList<room> getLargeRooms() {
        return largeRooms;
    }

    public ArrayList<room> getMedRooms() {
        return medRooms;
    }

    public ArrayList<room> getSmallRooms() {
        return smallRooms;
    }

}