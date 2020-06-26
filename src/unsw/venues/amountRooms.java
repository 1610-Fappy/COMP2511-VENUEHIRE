package unsw.venues;

public class amountRooms {
    private int largeRooms = 0;
    private int medRooms = 0;
    private int smallRooms = 0;

    public amountRooms() {
        super();
    }

    public amountRooms(int large, int medium, int small) {
        setRooms(large, medium, small);
    }

    private void setRooms(int large, int medium, int small) {
        this.largeRooms = large;
        this.medRooms = medium;
        this.smallRooms = small;
    }

    public void incrementLargeRooms() {
        setRooms(largeRooms + 1, medRooms, smallRooms);
    }

    public int getlargeRooms() {
        return largeRooms;
    }

    public void incrementMedRooms() {
        setRooms(largeRooms, medRooms + 1, smallRooms);
    }

    public int getmedRooms() {
        return medRooms;
    }
    
    public void incrementSmallRooms() {
        setRooms(largeRooms, medRooms, smallRooms + 1);
    }

    public int getsmallRooms() {
        return smallRooms;
    }
    
}