package guru.qa.models.CreateBooking;

import com.fasterxml.jackson.annotation.JsonInclude;
import guru.qa.models.CreateBooking.CreateBookingDatesRequest;
import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {
	private String firstname;
	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;
	private CreateBookingDatesRequest bookingdates;
	private String additionalneeds;
}