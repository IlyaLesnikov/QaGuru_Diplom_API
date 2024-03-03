package guru.qa.models.CreateBooking;

import guru.qa.models.CreateBooking.CreateBookingEntityResponse;
import lombok.Data;

@Data
public class CreateBookingResponse {
	private Integer bookingid;
	private CreateBookingEntityResponse booking;
}