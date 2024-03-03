package guru.qa.models.GetBooking;

import guru.qa.models.GetBooking.GetBookingDatesResponse;
import lombok.Data;

@Data
public class GetBookingResponse {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private GetBookingDatesResponse bookingdates;
    private String additionalneeds;
}
