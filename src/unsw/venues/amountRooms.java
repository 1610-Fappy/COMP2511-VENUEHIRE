package unsw.venues;

public class amountRooms {
    private int largeRooms = 0;
    private int medRooms = 0;
    private int smallRooms = 0;

    amountRooms() {
        super();
    }

    amountRooms(int large, int medium, int small) {
        setRooms(large, medium, small);
    }

    public void setRooms(int large, int medium, int small) {
        this.largeRooms = large;
        this.medRooms = medium;
        this.smallRooms = small;
    }

    public int getlargeRooms() {
        return largeRooms;
    }

    public int getmedRooms() {
        return medRooms;
    }
    
    public int getsmallRooms() {
        return smallRooms;
    }
    
}