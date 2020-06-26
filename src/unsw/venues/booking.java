package unsw.venues;

import java.time.LocalDate;

public class booking {
    
    private String id = "";
    private LocalDate startDate;
    private LocalDate enddDate;

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

    public LocalDate getEnddDate() {
        return enddDate;
    }
    
}