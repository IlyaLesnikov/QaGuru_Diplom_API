package guru.qa.models;

import lombok.Data;

@Data
public class GetBookingDatesResponse {
    private String checkin;
    private String checkout;
}
