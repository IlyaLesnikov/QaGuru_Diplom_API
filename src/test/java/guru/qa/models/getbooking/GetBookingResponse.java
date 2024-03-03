package guru.qa.models.getbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetBookingResponse {
    private String firstname;
    private String lastname;
    @JsonProperty("totalprice")
    private Integer totalPrice;
    private Boolean depositpaid;
    @JsonProperty("bookingdates")
    private GetBookingDatesResponse bookingDates;
    private String additionalneeds;
}
