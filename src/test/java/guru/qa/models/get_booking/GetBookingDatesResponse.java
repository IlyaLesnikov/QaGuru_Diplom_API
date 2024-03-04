package guru.qa.models.get_booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetBookingDatesResponse {
    @JsonProperty("checkin")
    private String checkIn;
    private String checkout;
}
