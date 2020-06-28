/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */

    private ArrayList<venue> venueList = new ArrayList<venue>();
    private ArrayList<String> venueNames = new ArrayList<String>();

    public VenueHireSystem() {
        // TODO Auto-generated constructor stub
    }

    private void processCommand(JSONObject json) {
        
        JSONObject result;
        
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        case "cancel":
            String id1 = json.getString("id");
            
            cancel(id1);
            break;
        
        case "change":
            String iD = json.getString("id");
            LocalDate start1 = LocalDate.parse(json.getString("start"));
            LocalDate end1 = LocalDate.parse(json.getString("end"));
            int small1 = json.getInt("small");
            int medium1 = json.getInt("medium");
            int large1 = json.getInt("large");

            result = change(iD, start1, end1, small1, medium1, large1);

            System.out.println(result.toString(2));
            break;
        // TODO Implement other commands
        }
    }

    private void addRoom(String venue, String room, String size) {

        venue newVenue;

        if (!venueNames.contains(venue)) {
            newVenue = new venue(venue);
            venueList.add(newVenue);
            venueNames.add(venue);
        }
        else {
            newVenue = venueList.get(venueNames.indexOf(venue));
        }
        newVenue.addRoom(room, size);
    }

    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();

        // TODO Process the request commmand

        ArrayList<venue> availVenue = checkRoomAmount(large, medium, small);
        ArrayList<room> availRoom = new ArrayList<room>();

        for (int i = 0; i < availVenue.size() && availRoom.size() < (large + medium + small); i++) {
            venue venueCheck = availVenue.get(i);
            availRoom = new ArrayList<room>();
            if (small > 0) {
                availRoom.addAll(venueCheck.checkRoomBooking(venueCheck.getSmallRooms(), id, small, start, end));
            }
            if (medium > 0) {
                availRoom.addAll(venueCheck.checkRoomBooking(venueCheck.getMedRooms(), id, medium, start, end));
            }
            if (large > 0) {
                availRoom.addAll(venueCheck.checkRoomBooking(venueCheck.getLargeRooms(), id, large, start, end));
            }
        }
        if (availVenue.size() > 0 && availRoom.size() == (large + medium + small)) {
            result.put("status", "success");
            result.put("venue", availVenue.get(0).getVenueName());
            result.put("rooms", convertArrayJSON(availRoom));
        }
        else {
            result.put("status", "rejected");
        }

        return result;
    }

    public JSONObject change(String id, LocalDate start, LocalDate end,
    int small, int medium, int large) {
        JSONObject result = new JSONObject();
        result.put("status", "");
        booking tempHold = new booking();
        for (int i = 0; i < venueList.size(); i++) {
            tempHold = venueList.get(i).cancelBooking(id);
            System.out.println(tempHold.getId());
            if (tempHold.getId().equals(id)) {
                result = request(id, start, end, small, medium, large);
                i = venueList.size();
            }
        }
        String status = result.getString("status");
        if (status.equals("rejected")) {
            for (int i = 0; i < tempHold.getbookedRooms().size(); i++) {
                tempHold.getbookedRooms().get(i).addBooking(tempHold, start, end);
            }
        }
        return result;
    }

    public void cancel(String id) {
        for (int i = 0; i < venueList.size(); i++) {
            venueList.get(i).cancelBooking(id);
        }
    }

    public ArrayList<venue> checkRoomAmount(int large, int medium, int small) {

        ArrayList<venue> availVenue = new ArrayList<venue>();
        
        for (int i = 0; i < venueList.size(); i++) {
            if (venueList.get(i).checkAmountRooms(large, medium, small)) {
                availVenue.add(venueList.get(i));
            }
        }
        return availVenue;
    }

    private JSONArray convertArrayJSON(ArrayList<room> roomList) {
        JSONArray rooms = new JSONArray();
        for (int i = 0; i < roomList.size(); i++) {
            rooms.put(roomList.get(i).getRoomName());
        }
        return rooms;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
