package guru.qa.models.CreateBooking;

import guru.qa.models.CreateBooking.CreateBookingDatesResponse;
import lombok.Data;

@Data
public class CreateBookingEntityResponse {
	private String firstname;
	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;
	private CreateBookingDatesResponse bookingdates;
	private String additionalneeds;
}